package com.blogspot.lucbors.uma.mobile.model;

import oracle.ateam.sample.mobile.v2.persistence.model.Entity;

import java.math.BigDecimal;

import java.util.Date;


public class Attendance
  extends Entity
{

  private BigDecimal id;
  private String name;
  private String username;
  private String company;
  private String rating;
  private BigDecimal attendeeId;
  private Boolean present;
  private String status;
  private BigDecimal sessionId;
  private String startTime;
  private Date date;
  private String hall;
  private String presenter;
  private String title;


  public BigDecimal getId()
  {
    return this.id;
  }

  public void setId(BigDecimal id)
  {
    this.id = id;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getUsername()
  {
    return this.username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getCompany()
  {
    return this.company;
  }

  public void setCompany(String company)
  {
    this.company = company;
  }

  public String getRating()
  {
    return this.rating;
  }

  public void setRating(String rating)
  {
    this.rating = rating;
  }

  public BigDecimal getAttendeeId()
  {
    return this.attendeeId;
  }

  public void setAttendeeId(BigDecimal attendeeId)
  {
    this.attendeeId = attendeeId;
  }

  public Boolean getPresent()
  {
    return this.present;
  }

  public void setPresent(Boolean present)
  {
    this.present = present;
  }

  public String getStatus()
  {
    return this.status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public BigDecimal getSessionId()
  {
    return this.sessionId;
  }

  public void setSessionId(BigDecimal sessionId)
  {
    this.sessionId = sessionId;
  }

  public String getStartTime()
  {
    return this.startTime;
  }

  public void setStartTime(String startTime)
  {
    this.startTime = startTime;
  }

  public Date getDate()
  {
    return this.date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public String getHall()
  {
    return this.hall;
  }

  public void setHall(String hall)
  {
    this.hall = hall;
  }

  public String getPresenter()
  {
    return this.presenter;
  }

  public void setPresenter(String presenter)
  {
    this.presenter = presenter;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }


}
