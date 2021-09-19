package com.jpa.example.demo.controller;

import com.jpa.example.demo.model.Book;
import com.jpa.example.demo.model.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

@Controller
@RequestMapping("/book")
public class BookController {

  private BookRepository bookRepository;

  @Autowired
  public BookController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @PostMapping("/create")
  public @ResponseBody Book insertBook(@RequestBody Book param) {
    if ( param.getName().isEmpty() ) {
      throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
    }

    Book newBook = new Book();
    newBook.setName(param.getName());

    return bookRepository.save(newBook);
  }

  @GetMapping("/selectAll")
  public @ResponseBody Iterable<Book> selectAllBooks() {
    return bookRepository.findAll();
  }
  
}
