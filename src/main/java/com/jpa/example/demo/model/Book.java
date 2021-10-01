package com.jpa.example.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = {
  @Index(name="idx_book_name", columnList = "name"),
  @Index(name="idx_book_name_published_date", columnList = "name, publishedDateOn"),
  @Index(name="unique_idx_book_name_author", columnList = "name, author", unique = true)
})
public class Book {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private Long author;
  private Date publishedDateOn;

  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public Long getAuthor() {
    return author;
  }
  
  public void setAuthor(Long author) {
    this.author = author;
  }

  public Date getPublishedDateOn() {
    return publishedDateOn;
  }

  public void setPublishedDateOn(Date publishedDateOn) {
    this.publishedDateOn = publishedDateOn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
