package com.marinhodev.domain;



import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
/**
 * @author Bruno Marinho
 */
@DynamoDBTable(tableName="Drivers")
public class Driver {

    @DynamoDBHashKey(attributeName = "id")
    private Integer id;
    private String name;
    private String carPlate;
    private Float latitude;
    private Float longitude;
    private boolean avaliable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public boolean isAvaliable() {
        return avaliable;
    }

    public void setAvaliable(boolean avaliable) {
        this.avaliable = avaliable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Driver driver = (Driver) o;

        if (avaliable != driver.avaliable) return false;
        if (carPlate != null ? !carPlate.equals(driver.carPlate) : driver.carPlate != null) return false;
        if (id != null ? !id.equals(driver.id) : driver.id != null) return false;
        if (latitude != null ? !latitude.equals(driver.latitude) : driver.latitude != null) return false;
        if (longitude != null ? !longitude.equals(driver.longitude) : driver.longitude != null) return false;
        if (name != null ? !name.equals(driver.name) : driver.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (carPlate != null ? carPlate.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (avaliable ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carPlate='" + carPlate + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", avaliable=" + avaliable +
                '}';
    }
}
