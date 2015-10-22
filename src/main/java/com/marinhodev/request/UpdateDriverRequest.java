package com.marinhodev.request;

/**
 * @author Bruno Marinho
 */
public class UpdateDriverRequest {

    private Integer id;
    private Float latitude;
    private Float longitude;
    private boolean avaliable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public String toString() {
        return "UpdateDriverRequest{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", avaliable=" + avaliable +
                '}';
    }
}
