package com.blogspot.lucbors.uma.mobile.model;

import oracle.ateam.sample.mobile.v2.persistence.model.Entity;

import java.math.BigDecimal;


public class Attendances extends Entity {

    private BigDecimal id;
    private String status;
    private BigDecimal attendeeId;
    private BigDecimal sessionId;
    private String name;
    private String username;
    private String company;
    private Boolean present;
    private String rating;
    private BigDecimal sessionsId;


    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAttendeeId() {
        return this.attendeeId;
    }

    public void setAttendeeId(BigDecimal attendeeId) {
        this.attendeeId = attendeeId;
    }

    public BigDecimal getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(BigDecimal sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Boolean getPresent() {
        return this.present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public BigDecimal getSessionsId() {
        return this.sessionsId;
    }

    public void setSessionsId(BigDecimal sessionsId) {
        this.sessionsId = sessionsId;
    }


}
