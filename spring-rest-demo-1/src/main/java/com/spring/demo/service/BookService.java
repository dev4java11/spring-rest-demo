package com.spring.demo.service;

import java.util.List;

import com.spring.demo.domain.Book;

public interface BookService {

	public Book findById(Integer id);
	public List<Book> listAllBooks();
	
	public Book create(Book book);
}
