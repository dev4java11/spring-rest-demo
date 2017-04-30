package com.spring.demo.controller.advice;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.demo.exception.DomainNotFoundException;
import com.spring.demo.util.ApiError;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	public RestResponseEntityExceptionHandler() {
		super();
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, apiError(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, apiError(HttpStatus.BAD_REQUEST, ex), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = {DataIntegrityViolationException.class})
	public final ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request){
		return handleExceptionInternal(ex, apiError(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(value = DomainNotFoundException.class)
	public final ResponseEntity<Object> handleDomainNotFoundException(final DomainNotFoundException ex, final WebRequest request){
		return handleExceptionInternal(ex, apiError(HttpStatus.NOT_FOUND, ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	private ApiError apiError(final HttpStatus httpStatus, final Exception ex){
		final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
		final String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
		
		return new ApiError(httpStatus.value(), message, developerMessage);
	}
}
