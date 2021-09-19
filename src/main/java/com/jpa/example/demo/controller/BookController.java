package com.jpa.example.demo.controller;

import com.jpa.example.demo.model.Book;
import com.jpa.example.demo.model.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

  private BookRepository bookRepository;

  @Autowired
  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Iterable<Book> selectAllBooks() {
    return bookRepository.findAll();
  }
  
}
