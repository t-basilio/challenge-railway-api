package com.my_ecommerce.domain.model;


import jakarta.persistence.*;

@Embeddable
public class Address {

    @Column(name = "zipcode_address")
    private String zipcode;

    @Column(name = "street_address")
    private String street;

    @Column(name = "number_address")
    private String number;

    @Column(name = "district_address")
    private String district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_address_id")
    private City city;

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}