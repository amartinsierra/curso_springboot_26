package com.minsait.buscador.init.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.minsait.buscador.init.exceptions.UrlRepetidaException;
import com.minsait.buscador.init.model.ErrorResponse;

@RestControllerAdvice
public class ExceptionsController {
	@ExceptionHandler(UrlRepetidaException.class)
	public ResponseEntity<ErrorResponse> exceptionUrl(UrlRepetidaException ex){
		return new ResponseEntity<>(new ErrorResponse(5,ex.getMessage()),HttpStatus.CONFLICT);
	}
	
}
