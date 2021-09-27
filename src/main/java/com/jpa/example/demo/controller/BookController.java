package com.jpa.example.demo.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpa.example.demo.model.Book;
import com.jpa.example.demo.model.BookCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

@Controller
@RequestMapping("/book")
public class BookController {

  private BookCrudRepository bookCrudRepository;

  @Autowired
  public BookController(BookCrudRepository bookCrudRepository) {
    this.bookCrudRepository = bookCrudRepository;
  }

  @PostMapping("/create")
  public @ResponseBody Book insertBook(@RequestBody Book param) {
    if ( param.getName().isEmpty() ) {
      throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
    }

    Book newBook = new Book();
    newBook.setName(param.getName());
    newBook.setPublishedDateOn(java.sql.Date.valueOf(LocalDate.now()));

    return bookCrudRepository.save(newBook);
  }

  @GetMapping("/select/name")
  public @ResponseBody Iterable<Book> selectByName(@RequestParam("bookName") String bookName) {
    List<Book> results =  bookCrudRepository.findAllByNameStartsWith(bookName);

    // 다음과 같이 Native Query를 작성할 수 있다.
    // List<Book> results =  bookCrudRepository.findAllByNameStartsWithNative(bookName);
    return results;
  }

  @Transactional
  @PostMapping("/update")
  public @ResponseBody int updateById(@RequestBody Book param) {
    // CrudRepository의 확장 없이 update하는 방법. @Transactional 이 필요 없다 ?
    // Book selectedBook = bookCrudRepository.findById(param.getId()).orElseThrow();
    // selectedBook.setName(param.getName());
    
    // return bookCrudRepository.save(selectedBook);

    // CrudRepository에 update문 추가
    int updatedCnt = bookCrudRepository.updateBookSetNameForId(param.getId(), param.getName());
    System.out.println("Updated count:"+updatedCnt);

    return updatedCnt;
  }

  @PostMapping("/delete")
  public @ResponseBody Map<String, String> deleteById(@RequestBody Book param) {
    bookCrudRepository.delete(param);
    
    // TODO: DML 공통 처리 Response 작성
    Map<String, String> result = new HashMap<>();
    result.put("message", "success");

    return result;
  }

  @GetMapping("/selectAll")
  public @ResponseBody Iterable<Book> selectAllBooks() {
    return bookCrudRepository.findAll();
  }
  
}
