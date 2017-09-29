package com.beyond.algo.model;

public class Paymentinfo {
    private String pyisn;

    private String usrsn;

    private String street;

    private String city;

    private String country;

    private String zipcode;

    public String getPyisn() {
        return pyisn;
    }

    public void setPyisn(String pyisn) {
        this.pyisn = pyisn == null ? null : pyisn.trim();
    }

    public String getUsrsn() {
        return usrsn;
    }

    public void setUsrsn(String usrsn) {
        this.usrsn = usrsn == null ? null : usrsn.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }
}