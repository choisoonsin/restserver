package com.jpa.example.demo.model;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository<Book, Long>{
  
  @Query("select m from #{#entityName} m where m.name like :name%")
  List<Book> findAllByNameStartsWith(@Param("name") String name);

  @Query(value = "select * from book m where m.name like :name%", nativeQuery = true) 
  List<Book> findAllByNameStartsWithNative(@Param("name") String name);

  @Modifying
  @Query("update #{#entityName} m set name = :name where id = :id") 
  int updateBookSetNameForId(@Param("id") Long id, @Param("name") String name);

}
