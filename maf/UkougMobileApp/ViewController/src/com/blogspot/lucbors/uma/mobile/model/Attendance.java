package com.blogspot.lucbors.uma.mobile.model;

import oracle.ateam.sample.mobile.v2.persistence.model.Entity;

import java.math.BigDecimal;


public class Attendance extends Entity {

    private BigDecimal attendeeId;
    private Boolean present;
    private String status;
    private BigDecimal sessionId;
    private BigDecimal id;
    private String rating;


    public BigDecimal getAttendeeId() {
        return this.attendeeId;
    }

    public void setAttendeeId(BigDecimal attendeeId) {
        this.attendeeId = attendeeId;
    }

    public Boolean getPresent() {
        return this.present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(BigDecimal sessionId) {
        this.sessionId = sessionId;
    }

    public BigDecimal getId() {
        return this.id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


}
