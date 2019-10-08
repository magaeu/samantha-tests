package com.qa.dto;

public class UserDTO {

    private int id;
    private String name;
    private String username;
    private String email;
    private AddressDTO address;
    private String phone;
    private String website;
    private CompanyDTO company;

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public UserDTO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public UserDTO setAddress(AddressDTO address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public UserDTO setWebsite(String website) {
        this.website = website;
        return this;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public UserDTO setCompany(CompanyDTO company) {
        this.company = company;
        return this;
    }

}
