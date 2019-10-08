package com.qa.dto;

public class GeoDTO {

    private String lat;
    private String lng;

    public GeoDTO() {
    }

    public String getLat() {
        return lat;
    }

    public GeoDTO setLat(String lat) {
        this.lat = lat;
        return this;
    }

    public String getLng() {
        return lng;
    }

    public GeoDTO setLng(String lng) {
        this.lng = lng;
        return this;
    }

}
