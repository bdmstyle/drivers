package com.marinhodev.request;

/**
 * @author Bruno Marinho
 */
public class CreateDriverRequest {

    private Integer id;
    private String name;
    private String carPlate;

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

    @Override
    public String toString() {
        return "CreateDriverRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carPlate='" + carPlate + '\'' +
                '}';
    }
}
