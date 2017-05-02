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
import com.spring.demo.domain.User;
import com.spring.demo.repository.BookRepository;
import com.spring.demo.repository.UserRepository;
import com.spring.demo.util.Constant;


@SpringBootApplication
//@Import(WebConfig.class)
public class SpringRestDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestDemo1Application.class, args);
	}
	
	
	
	@Component
	public class InitData implements CommandLineRunner{
		
		private BookRepository bookRepository;
		
		private UserRepository userRepository;
		
		@Autowired
		public void setBookRepository(BookRepository bookRepository) {
			this.bookRepository = bookRepository;
		}
		
		@Autowired
		public void setUserRepository(UserRepository userRepository) {
			this.userRepository = userRepository;
		}
		
		@Override
		public void run(String... args) throws Exception {
			initBookData();
			initUserData();
		}
		
		public void initBookData(){
			Book book1 = new Book();
			book1.setUuid(UUID.randomUUID().toString());
			book1.setName("El Ingenioso Hidalgo Don Quijote de la Mancha");
			book1.setDescription("Obra escrita por Miguel de Cervantes Saavedra.");
			book1.setYearOfPublish(1992);
			book1.setEmail("cervantes@book.com");
			
			Book book2 = new Book();
			book2.setUuid(UUID.randomUUID().toString());
			book2.setName("Los Heraldos Negros");
			book2.setDescription("Obra escrita por Cesar Vallejo.");
			book2.setYearOfPublish(1930);
			book2.setEmail("vallejo@book.com");
			
			Book book3 = new Book();
			book3.setUuid(UUID.randomUUID().toString());
			book3.setName("El viejo y el mar");
			book3.setDescription("Obra escrita por Hernest Hemingway.");
			book3.setYearOfPublish(1940);
			book3.setEmail("hemingway@book.com");
			
			Book book4 = new Book();
			book4.setUuid(UUID.randomUUID().toString());
			book4.setName("La Divina Comedia");
			book4.setDescription("Obra escrita por Dante Aliguieri.");
			book4.setYearOfPublish(1750);
			book4.setEmail("aliguieri@book.com");
			
			bookRepository.save(book1);
			bookRepository.save(book2);
			bookRepository.save(book3);
			bookRepository.save(book4);
		}
	
		public void initUserData(){
			User user1 = new User();
			user1.setUuid(UUID.randomUUID().toString());
			user1.setUsername("admin");
			user1.setPassword("admin");
			user1.setFullName("Admin A. Rovers");
			user1.setEmail("admin@spring.com");
			user1.setStatus(1);
			user1.setRole(Constant.ROLE_CAN_WRITE);
			
			User user2 = new User();
			user2.setUuid(UUID.randomUUID().toString());
			user2.setUsername("visitor");
			user2.setPassword("visitor");
			user2.setFullName("Visitor V. Rovers");
			user2.setEmail("visitor@spring.com");
			user2.setStatus(1);
			user2.setRole(Constant.ROLE_CAN_READ);
			
			userRepository.save(user1);
			userRepository.save(user2);
		}
		
	
	}
	
}
