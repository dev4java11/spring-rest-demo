package com.spring.demo.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonMessageConverter());
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper());
		return converter;
	}
	
	@Bean
	public ObjectMapper objectMapper(){
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		mapper.enable(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID);
		return mapper;
	}
	
	
}