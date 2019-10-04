package com.qa.dto;

import java.util.Objects;

public class GeoDTO {

    private String lat;
    private String lng;

    public GeoDTO() {
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoDTO geoDTO = (GeoDTO) o;
        return Objects.equals(lat, geoDTO.lat) &&
                Objects.equals(lng, geoDTO.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }

    @Override
    public String toString() {
        return "GeoDTO{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
