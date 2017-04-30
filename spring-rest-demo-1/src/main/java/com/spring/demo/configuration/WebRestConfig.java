package com.spring.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.spring.demo.domain.Book;

@Configuration
public class WebRestConfig extends RepositoryRestConfigurerAdapter{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Book.class);
	}
}
