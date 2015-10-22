package com.marinhodev.domain;


import com.marinhodev.request.CreateDriverRequest;
import com.marinhodev.request.UpdateDriverRequest;

/**
* @author Bruno Marinho
* This class is responsible for manage drivers
*/
public class DriverManager {

    private DynamoClient dynamoClient;

    public DriverManager() {
        dynamoClient = new DynamoClient();

    }

    public void createDriver(CreateDriverRequest request){

        Driver newDriver = new Driver();
        newDriver.setId(request.getId());
        newDriver.setCarPlate(request.getCarPlate());
        newDriver.setName(request.getName());
        newDriver.setAvaliable(false);
        newDriver.setLatitude(0f);
        newDriver.setLongitude(0f);

        dynamoClient.saveDriver(newDriver);

    }

    public void updateDriverPosition(UpdateDriverRequest request){

        Driver driver = dynamoClient.getDriveById(request.getId());

        if(driver == null)
            throw new RuntimeException("driver not found");


        driver.setId(request.getId());
        driver.setAvaliable(request.isAvaliable());
        driver.setLatitude(request.getLatitude());
        driver.setLongitude(request.getLongitude());

        dynamoClient.saveDriver(driver);

    }


    public Driver driverDetails(Integer driverId){

        Driver driver = dynamoClient.getDriveById(driverId);

        if(driver == null)
            throw new RuntimeException("driver not found");

        return driver;
    }

}
