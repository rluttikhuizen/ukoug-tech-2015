package com.blogspot.lucbors.uma.mobile.model;

import oracle.ateam.sample.mobile.v2.persistence.model.Entity;

import java.math.BigDecimal;


public class Speakers extends Entity {

    private String company;
    private String firstname;
    private BigDecimal id;
    private String lastname;
    private String bio;


    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


}
