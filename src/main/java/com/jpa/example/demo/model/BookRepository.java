package com.jpa.example.demo.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>{
  
  List<Book> findAllByName(String name);

}
