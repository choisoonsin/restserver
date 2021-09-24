package com.jpa.example.demo.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private Date publishedDateOn;

  public Long getId() {
    return id;
  }

  public Date getPublishedDateOn() {
    return publishedDateOn;
  }

  public void setPublishedDateOn(Date publishedDateOn) {
    this.publishedDateOn = publishedDateOn;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
