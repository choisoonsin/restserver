package com.jpa.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReadOnlyRepository extends JpaRepository<Book, Long> {
    
}
