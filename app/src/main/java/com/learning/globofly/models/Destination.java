package com.learning.globofly.models;

public class Destination {
    String city;
    String description;
    String country;

    public Destination(String city, String country, String description) {
        this.city = city;
        this.description = description;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
