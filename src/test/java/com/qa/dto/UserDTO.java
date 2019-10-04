package com.qa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private int id;
    private String name;
    private String username;
    private String email;
//    private AddressDTO address;
//    private String phone;
//    private String website;
//    private CompanyDTO company;

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

//    public AddressDTO getAddress() {
//        return address;
//    }
//
//    public UserDTO setAddress(AddressDTO address) {
//        this.address = address;
//        return this;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public UserDTO setPhone(String phone) {
//        this.phone = phone;
//        return this;
//    }
//
//    public String getWebsite() {
//        return website;
//    }
//
//    public UserDTO setWebsite(String website) {
//        this.website = website;
//        return this;
//    }
//
//    public CompanyDTO getCompany() {
//        return company;
//    }
//
//    public UserDTO setCompany(CompanyDTO company) {
//        this.company = company;
//        return this;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserDTO userDTO = (UserDTO) o;
//        return id == userDTO.id &&
//                Objects.equals(name, userDTO.name) &&
//                Objects.equals(username, userDTO.username) &&
//                Objects.equals(email, userDTO.email) &&
//                Objects.equals(address, userDTO.address) &&
//                Objects.equals(phone, userDTO.phone) &&
//                Objects.equals(website, userDTO.website) &&
//                Objects.equals(company, userDTO.company);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, username, email, address, phone, website, company);
//    }
//
//    @Override
//    public String toString() {
//        return "UserDTO{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", address=" + address +
//                ", phone='" + phone + '\'' +
//                ", website='" + website + '\'' +
//                ", company=" + company +
//                '}';
//    }
}
