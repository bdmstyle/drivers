package com.marinhodev.domain;

import com.marinhodev.request.SearchDriverRequest;

/**
 * @author Bruno Marinho
 */
public class Area {

    private Float x1,x2,y1,y2;

    public Area(SearchDriverRequest request) {
        x1 = Float.parseFloat(request.getNe().split(",")[0]);
        y1 = Float.parseFloat(request.getNe().split(",")[1]);
        x2 = Float.parseFloat(request.getSw().split(",")[0]);
        y2 = Float.parseFloat(request.getSw().split(",")[1]);
    }

    public Float getX1() {
        return x1;
    }

    public void setX1(Float x1) {
        this.x1 = x1;
    }

    public Float getX2() {
        return x2;
    }

    public void setX2(Float x2) {
        this.x2 = x2;
    }

    public Float getY1() {
        return y1;
    }

    public void setY1(Float y1) {
        this.y1 = y1;
    }

    public Float getY2() {
        return y2;
    }

    public void setY2(Float y2) {
        this.y2 = y2;
    }
}
