package com.blogspot.lucbors.uma.mobile.model;

import oracle.ateam.sample.mobile.v2.persistence.model.Entity;

import java.math.BigDecimal;


public class Attendance extends Entity {

    private BigDecimal id;
       private String status;
       private BigDecimal attendeeId;
       private BigDecimal sessionId;
       private String name;
       private String username;
       private String company;
       private Boolean present;
       private String rating;

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAttendeeId(BigDecimal attendeeId) {
        this.attendeeId = attendeeId;
    }

    public BigDecimal getAttendeeId() {
        return attendeeId;
    }

    public void setSessionId(BigDecimal sessionId) {
        this.sessionId = sessionId;
    }

    public BigDecimal getSessionId() {
        return sessionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

}
