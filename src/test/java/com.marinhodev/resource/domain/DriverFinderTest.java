package com.marinhodev.resource.domain;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.marinhodev.domain.Area;
import com.marinhodev.domain.Driver;
import com.marinhodev.domain.DriverFinder;
import com.marinhodev.domain.DynamoClient;
import com.marinhodev.request.PlaceRequest;
import com.marinhodev.request.SearchDriverRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Bruno Marinho
 */
@RunWith(MockitoJUnitRunner.class)
public class DriverFinderTest {

    @Mock
    private DynamoClient dynamoClient;

    @InjectMocks
    private DriverFinder driverFinder;

    /**
     * To understand the test look at resources/map.png and testData.txt
     *
     * Search Area is composed by point [4] and [5] in map
     *
     * In this case the 3 taxi is avaliable but taxi 2 is out of bounds then i get taxi1 and taxi3
     */
    @Test
    public void listAllAvaliableDriversInArea(){

        SearchDriverRequest request = new SearchDriverRequest();

        request.setNe("-23.570251,-46.712659");
        request.setSw("-23.572867,-46.706973");

        Driver taxi1 = new Driver();
        taxi1.setId(1);
        taxi1.setName("taxi 1");
        taxi1.setCarPlate("plate 1");
        taxi1.setAvaliable(true);
        taxi1.setLatitude(-23.571431f);
        taxi1.setLongitude(-46.707961f);

        Driver taxi2 = new Driver();
        taxi2.setId(2);
        taxi2.setName("taxi 2");
        taxi2.setCarPlate("plate 2");
        taxi2.setAvaliable(true);
        taxi2.setLatitude(-23.575051f);
        taxi2.setLongitude(-46.713625f);

        Driver taxi3 = new Driver();
        taxi3.setId(3);
        taxi3.setName("taxi 3");
        taxi3.setCarPlate("plate 3");
        taxi3.setAvaliable(true);
        taxi3.setLatitude(-23.571647f);
        taxi3.setLongitude(-46.711715f);

        List<Driver> driversList = new ArrayList<>();
        driversList.add(taxi1);
        driversList.add(taxi2);
        driversList.add(taxi3);

        when(dynamoClient.getAllDrivers()).thenReturn(driversList);
        when(dynamoClient.getDriveById(1)).thenReturn(taxi1);
        when(dynamoClient.getDriveById(2)).thenReturn(taxi2);
        when(dynamoClient.getDriveById(3)).thenReturn(taxi3);

        List<Driver> drivers = driverFinder.listDriversInArea(new Area(request));

        assertThat(drivers.size(), equalTo(2));

        drivers.forEach(
                driver -> assertThat(driver.isAvaliable(), equalTo(true))
        );

    }

    /**
     * In this case is equals to previous but the taxi1 is busy then only taxi3 is avaliable.
     */
    @Test
    public void listAllAvaliableDriversInAreaWithTaxi1Busy(){

        SearchDriverRequest request = new SearchDriverRequest();

        request.setNe("-23.570251,-46.712659");
        request.setSw("-23.572867,-46.706973");

        Driver taxi1 = new Driver();
        taxi1.setId(1);
        taxi1.setName("taxi 1");
        taxi1.setCarPlate("plate 1");
        taxi1.setAvaliable(false);
        taxi1.setLatitude(-23.571431f);
        taxi1.setLongitude(-46.707961f);

        Driver taxi2 = new Driver();
        taxi2.setId(2);
        taxi2.setName("taxi 2");
        taxi2.setCarPlate("plate 2");
        taxi2.setAvaliable(true);
        taxi2.setLatitude(-23.575051f);
        taxi2.setLongitude(-46.713625f);

        Driver taxi3 = new Driver();
        taxi3.setId(3);
        taxi3.setName("taxi 3");
        taxi3.setCarPlate("plate 3");
        taxi3.setAvaliable(true);
        taxi3.setLatitude(-23.571647f);
        taxi3.setLongitude(-46.711715f);

        List<Driver> driversList = new ArrayList<>();
        driversList.add(taxi1);
        driversList.add(taxi2);
        driversList.add(taxi3);

        when(dynamoClient.getAllDrivers()).thenReturn(driversList);
        when(dynamoClient.getDriveById(1)).thenReturn(taxi1);
        when(dynamoClient.getDriveById(2)).thenReturn(taxi2);
        when(dynamoClient.getDriveById(3)).thenReturn(taxi3);

        List<Driver> drivers = driverFinder.listDriversInArea(new Area(request));

        assertThat(drivers.size(), equalTo(1));

        drivers.forEach(
                driver -> assertThat(driver.isAvaliable(), equalTo(true))
        );

    }

    /**
     * Again see in map to undertand that taxi 1 is nearest driver to place request [6] point in map
     * */
    @Test
    public void allAvaliableTaxiGetNearestDriver() {

        PlaceRequest request = new PlaceRequest();

        request.setLatitude(-23.571392f);
        request.setLongitude(-46.709462f);

        Driver taxi1 = new Driver();
        taxi1.setId(1);
        taxi1.setName("taxi 1");
        taxi1.setCarPlate("plate 1");
        taxi1.setAvaliable(true);
        taxi1.setLatitude(-23.571431f);
        taxi1.setLongitude(-46.707961f);

        Driver taxi2 = new Driver();
        taxi2.setId(2);
        taxi2.setName("taxi 2");
        taxi2.setCarPlate("plate 2");
        taxi2.setAvaliable(true);
        taxi2.setLatitude(-23.575051f);
        taxi2.setLongitude(-46.713625f);

        Driver taxi3 = new Driver();
        taxi3.setId(3);
        taxi3.setName("taxi 3");
        taxi3.setCarPlate("plate 3");
        taxi3.setAvaliable(true);
        taxi3.setLatitude(-23.571647f);
        taxi3.setLongitude(-46.711715f);

        List<Driver> driversList = new ArrayList<>();
        driversList.add(taxi1);
        driversList.add(taxi2);
        driversList.add(taxi3);

        when(dynamoClient.getAllDrivers()).thenReturn(driversList);
        when(dynamoClient.getDriveById(1)).thenReturn(taxi1);
        when(dynamoClient.getDriveById(2)).thenReturn(taxi2);
        when(dynamoClient.getDriveById(3)).thenReturn(taxi3);

        Driver nearestDriver = driverFinder.nearestDriver(request)
                .orElseThrow(() -> new RuntimeException("None Driver Found"));

        assertThat(nearestDriver, equalTo(taxi1));

    }

}
