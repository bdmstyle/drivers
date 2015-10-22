package com.marinhodev.resource.domain;


import com.marinhodev.domain.Driver;
import com.marinhodev.domain.DriverManager;
import com.marinhodev.domain.DynamoClient;
import com.marinhodev.request.CreateDriverRequest;
import com.marinhodev.request.UpdateDriverRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Bruno Marinho
 */
@RunWith(MockitoJUnitRunner.class)
public class DriverManagerTest {

    @Mock
    private DynamoClient dynamoClient;

    @InjectMocks
    private DriverManager driverManager;

    @Test
    public void createDriver(){

        CreateDriverRequest request = new CreateDriverRequest();

        request.setCarPlate("plate");
        request.setId(1);
        request.setName("bruno");

        driverManager.createDriver(request);

        Driver expectedDriver = new Driver();
        expectedDriver.setId(request.getId());
        expectedDriver.setCarPlate(request.getCarPlate());
        expectedDriver.setName(request.getName());
        expectedDriver.setAvaliable(false);
        expectedDriver.setLatitude(0f);
        expectedDriver.setLongitude(0f);


        verify(dynamoClient, times(1)).saveDriver(expectedDriver);

    }

    @Test
    public void updateDriverPosition(){

        UpdateDriverRequest request = new UpdateDriverRequest();

        request.setId(1);
        request.setAvaliable(true);
        request.setLatitude(0f);
        request.setLongitude(0f);

        when(dynamoClient.getDriveById(1)).thenReturn(new Driver());

        driverManager.updateDriverPosition(request);

        Driver expectedDriver = new Driver();
        expectedDriver.setAvaliable(true);
        expectedDriver.setId(1);
        expectedDriver.setLatitude(0f);
        expectedDriver.setLongitude(0f);

        verify(dynamoClient, times(1)).getDriveById(1);
        verify(dynamoClient, times(1)).saveDriver(expectedDriver);

    }

    @Test
    public void driverDetails(){

        Driver driver = new Driver();

        when(dynamoClient.getDriveById(1)).thenReturn(driver);

        Driver expectedDriver = driverManager.driverDetails(1);

        verify(dynamoClient, times(1)).getDriveById(1);

        assertThat(driver, equalTo(expectedDriver));

    }

}
