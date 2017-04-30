package com.spring.demo.service;

import java.util.List;

import com.spring.demo.domain.Book;

public interface BookService {

	public Book findById(Integer id);
	
	public Book findByUuid(String uuid);
	
	public List<Book> listAllBooks();
	
	public Book create(Book book);
	
	public Book update(Book book);
	
	public Book delete(Integer id);
	
	public Book delete(String uuid);
	
	public Book delete(Book book);
}
