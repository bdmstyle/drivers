package com.marinhodev.request;

/**
 * @author Bruno Marinho
 */
public class SearchDriverRequest {

    private String sw;
    private String ne;

    public String getSw() {
        return sw;
    }

    public void setSw(String sw) {
        this.sw = sw;
    }

    public String getNe() {
        return ne;
    }

    public void setNe(String ne) {
        this.ne = ne;
    }

    @Override
    public String toString() {
        return "SearchDriverRequest{" +
                "sw='" + sw + '\'' +
                ", ne='" + ne + '\'' +
                '}';
    }
}
