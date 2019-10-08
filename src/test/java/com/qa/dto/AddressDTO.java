package com.qa.dto;

public class AddressDTO {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDTO geo;

    public AddressDTO() {
    }

    public String getStreet() {
        return street;
    }

    public AddressDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getSuite() {
        return suite;
    }

    public AddressDTO setSuite(String suite) {
        this.suite = suite;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public AddressDTO setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public GeoDTO getGeo() {
        return geo;
    }

    public AddressDTO setGeo(GeoDTO geo) {
        this.geo = geo;
        return this;
    }

}
