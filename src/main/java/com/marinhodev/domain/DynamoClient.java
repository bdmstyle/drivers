package com.marinhodev.domain;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.util.List;
/**
@author Bruno
 Hide complexity of Amazon api
 */
public class DynamoClient {

    private DynamoDBMapper mapper;

    public DynamoClient() {
        AmazonDynamoDBClient amazonDynamoDBClient = new AmazonDynamoDBClient();
        amazonDynamoDBClient.setRegion(Region.getRegion(Regions.US_EAST_1));
        mapper = new DynamoDBMapper(amazonDynamoDBClient);
    }

    public List<Driver> getAllDrivers(){
        return mapper.scan(Driver.class, new DynamoDBScanExpression());
    }

    public Driver getDriveById(Integer id){
        return mapper.load(Driver.class, id.longValue());
    }

    public void saveDriver(Driver driver){
        mapper.save(driver);
    }



 }
