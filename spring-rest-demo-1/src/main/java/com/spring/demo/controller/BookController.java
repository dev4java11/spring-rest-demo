package com.spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.domain.Book;
import com.spring.demo.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	private BookService service;
	
	@Autowired
	public void setService(BookService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Book>> findAll(){
		List<Book> books = service.listAllBooks();
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> findById(@PathVariable(name = "id", required = true) Integer id){
		Book book = service.findById(id);
		return ResponseEntity.ok(book);
	}
	
	@PostMapping
	public ResponseEntity<Book> create(@RequestBody Book book){
		book = service.create(book);
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}
	
}
