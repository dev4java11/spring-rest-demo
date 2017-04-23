package com.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.demo.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

	
}
