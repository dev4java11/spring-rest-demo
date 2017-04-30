package com.spring.demo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.demo.domain.Book;
import com.spring.demo.exception.DomainNotFoundException;
import com.spring.demo.repository.BookRepository;
import com.spring.demo.service.BookService;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

	private BookRepository repository;
	
	@Autowired
	public void setRepository(BookRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Book findById(Integer id) {
		Book book = repository.findOne(id);
		if(book == null){
			throw new DomainNotFoundException(Book.class, id);
		}
		return book;
	}
	
	@Override
	public Book findByUuid(String uuid) {
		Book book = repository.findByUuid(uuid);
		if(book == null){
			throw new DomainNotFoundException(Book.class, uuid);
		}
		return book;
	}

	@Override
	public List<Book> listAllBooks() {
		return repository.findAll();
	}
	
	@Transactional
	@Override
	public Book create(Book book) {
		if(book.getUuid() == null || book.getUuid().trim().isEmpty()){
			book.setUuid(UUID.randomUUID().toString());
		}
		book = repository.save(book);
		return book;
	}

	@Transactional
	@Override
	public Book update(Book book) {
		Book bookDB = repository.findOne(book.getId());
		if(bookDB == null){
			throw new DomainNotFoundException(Book.class, book.getId());
		}
		
		bookDB.setName(book.getName());
		bookDB.setDescription(book.getDescription());
		bookDB.setYearOfPublish(book.getYearOfPublish());
		bookDB.setEmail(book.getEmail());
		
		return repository.save(bookDB);
	}
	
	@Transactional
	@Override
	public Book delete(Integer id) {
		Book book = repository.findOne(id);
		if(book == null){
			throw new DomainNotFoundException(Book.class, id);
		}
		repository.delete(id);
		return book;
	}
	
	@Transactional
	@Override
	public Book delete(String uuid) {
		Book book = repository.findByUuid(uuid);
		if(book == null){
			throw new DomainNotFoundException(Book.class, uuid);
		}
		repository.delete(book);
		return book;
	}
	
	@Transactional
	@Override
	public Book delete(Book book) {
		boolean exists = repository.exists(book.getId());
		if(!exists){
			throw new DomainNotFoundException(Book.class, book.getId());
		}
		repository.delete(book);
		return book;
	}
}
