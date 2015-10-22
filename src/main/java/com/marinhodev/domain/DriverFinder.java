package com.marinhodev.domain;

import com.infomatiq.jsi.Point;
import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.SpatialIndex;
import com.infomatiq.jsi.rtree.RTree;
import com.marinhodev.request.PlaceRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author Bruno Marinho
 * This class is responsible for searchs
 */
public class DriverFinder {

    private DynamoClient dynamoClient;
    private SpatialIndex world;

    public DriverFinder() {
        this.dynamoClient = new DynamoClient();
        world = new RTree();
        world.init(null);

    }

    public List<Driver> listDriversInArea(Area area){

        loadDrivers();

        List<Integer> driverIds= new ArrayList<>();

        world.contains(new Rectangle(area.getX1(), area.getY1(), area.getX2(), area.getY2()),driverIds::add);

        return driverIds
                    .stream()
                        .map(dynamoClient::getDriveById)
                            .filter(Driver::isAvaliable)
                                .collect((toList()));

    }

    public Optional<Driver> nearestDriver(PlaceRequest placeRequest){

        loadDrivers();

        List<Integer> driverIds= new ArrayList<>();
        world.nearestN(new Point(placeRequest.getLatitude(), placeRequest.getLongitude()),driverIds::add,10,Float.MAX_VALUE);

        return driverIds
                .stream()
                    .map(dynamoClient::getDriveById)
                       .filter(Driver::isAvaliable)
                           .findFirst();

    }

    private void loadDrivers() {

        dynamoClient.getAllDrivers().forEach(driver -> {

                    //driver in lib is a Rectangle
                    Rectangle rectangle = new Rectangle(driver.getLatitude().floatValue(), driver.getLongitude().floatValue(),
                            driver.getLatitude().floatValue(), driver.getLongitude().floatValue());

                    world.add(rectangle, driver.getId());
                }
        );
    }
}
