package com.marinhodev.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.marinhodev.domain.Area;
import com.marinhodev.domain.DriverFinder;
import com.marinhodev.domain.DriverManager;
import com.marinhodev.request.PlaceRequest;
import com.marinhodev.request.CreateDriverRequest;
import com.marinhodev.request.SearchDriverRequest;
import com.marinhodev.request.UpdateDriverRequest;

/**
 * @author Bruno Marinho
 */
public class DriverResource {

    private DriverFinder driverFinder;
    private DriverManager driverManager;

    public DriverResource() {
        //real life DI here
        driverFinder = new DriverFinder();
        driverManager = new DriverManager();
    }

    public void createDriver(CreateDriverRequest request){

        //do validation, log , format output and other controller stuffs

        driverManager.createDriver(request);

    }

    public void updateDriverPosition(UpdateDriverRequest request){

        //do validation, log , format output and other controller stuffs

        driverManager.updateDriverPosition(request);

    }


    public String driverDetails(Integer driverId){

        //do validation, log , format output and other controller stuffs

        return toJson(driverManager.driverDetails(driverId));
    }



    public String listDriversInArea(SearchDriverRequest request){

        //do validation, log , format output and other controller stuffs

        return toJson(driverFinder.listDriversInArea(new Area(request)));

    }

    public String nearestDriver(PlaceRequest placeRequest) throws JsonProcessingException {

        //do validation, log , format output and other controller stuffs

        return toJson(driverFinder.nearestDriver(placeRequest)
                .orElseThrow(() -> new RuntimeException("None Driver Found")));
    }

    private String toJson(Object object){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
