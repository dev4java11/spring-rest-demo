package com.spring.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.domain.Book;
import com.spring.demo.service.BookService;
import com.spring.demo.util.Constant;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	private BookService service;
	
	@Autowired
	public void setService(BookService service) {
		this.service = service;
	}

	@GetMapping
	@Secured(value = {Constant.ROLE_CAN_READ, Constant.ROLE_CAN_WRITE})
	public ResponseEntity<List<Book>> findAll(){
		List<Book> books = service.listAllBooks();
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/{id}")
	@Secured(value = {Constant.ROLE_CAN_READ, Constant.ROLE_CAN_WRITE})
	public ResponseEntity<Book> findById(@PathVariable(name = "id", required = true) Integer id){
		Book book = service.findById(id);
		return ResponseEntity.ok(book);
	}
	
	@GetMapping("/uuid/{uuid}")
	@Secured(value = {Constant.ROLE_CAN_READ, Constant.ROLE_CAN_WRITE})
	public ResponseEntity<Book> findByUuid(@PathVariable(name = "uuid", required = true) String uuid){
		Book book = service.findByUuid(uuid);
		return ResponseEntity.ok(book);
	}
	
	@PostMapping
	@Secured(Constant.ROLE_CAN_WRITE)
	public ResponseEntity<Book> create(@RequestBody @Valid Book book){
		book = service.create(book);
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Secured(Constant.ROLE_CAN_WRITE)
	public ResponseEntity<Book> update(@PathVariable(name = "id", required = true) Integer id, @RequestBody @Valid Book book){
		book.setId(id);
		book = service.update(book);
		return ResponseEntity.ok(book);
	}
	
	@PutMapping("/uuid/{uuid}")
	@Secured(Constant.ROLE_CAN_WRITE)
	public ResponseEntity<Book> updateByUuid(@PathVariable(name = "uuid", required = true) String uuid, @RequestBody @Valid Book book){
		book.setUuid(uuid);
		book = service.updateByUuid(book);
		return ResponseEntity.ok(book);
	}
	
	@DeleteMapping("/{id}")
	@Secured(Constant.ROLE_CAN_WRITE)
	public ResponseEntity<Book> delete(@PathVariable(name = "id", required = true) Integer id){
		Book book = service.delete(id);
		return ResponseEntity.ok(book);
	}
	
	@DeleteMapping("/uuid/{uuid}")
	@Secured(Constant.ROLE_CAN_WRITE)
	public ResponseEntity<Book> delete(@PathVariable(name = "uuid", required = true) String uuid){
		Book book = service.deleteByUuid(uuid);
		return ResponseEntity.ok(book);
	}
}
