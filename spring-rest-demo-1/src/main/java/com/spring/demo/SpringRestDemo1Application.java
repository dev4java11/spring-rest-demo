package com.spring.demo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring.demo.configuration.WebConfig;
import com.spring.demo.domain.Book;
import com.spring.demo.repository.BookRepository;


@SpringBootApplication
//@Import(WebConfig.class)
public class SpringRestDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestDemo1Application.class, args);
	}
	
	
	
	@Component
	public class InitData implements CommandLineRunner{
		
		private BookRepository bookRepository;
		
		@Autowired
		public void setBookRepository(BookRepository bookRepository) {
			this.bookRepository = bookRepository;
		}
		
		@Override
		public void run(String... args) throws Exception {
			Book book1 = new Book();
//			book1.setId("1");
			book1.setUuid(UUID.randomUUID().toString());
			book1.setName("El Ingenioso Hidalgo Don Quijote de la Mancha");
			book1.setDescription("Obra escrita por Miguel de Cervantes Saavedra.");
			
			Book book2 = new Book();
//			book2.setId("2");
			book2.setUuid(UUID.randomUUID().toString());
			book2.setName("Los Heraldos Negros");
			book2.setDescription("Obra escrita por Cesar Vallejo.");
			
			Book book3 = new Book();
//			book3.setId("3");
			book3.setUuid(UUID.randomUUID().toString());
			book3.setName("El viejo y el mar");
			book3.setDescription("Obra escrita por Hernest Hemingway.");
			
			Book book4 = new Book();
//			book4.setId("4");
			book4.setUuid(UUID.randomUUID().toString());
			book4.setName("La Divina Comedia");
			book4.setDescription("Obra escrita por Dante Aliguieri.");
			
			bookRepository.save(book1);
			bookRepository.save(book2);
			bookRepository.save(book3);
			bookRepository.save(book4);
		}
	}
	
}
