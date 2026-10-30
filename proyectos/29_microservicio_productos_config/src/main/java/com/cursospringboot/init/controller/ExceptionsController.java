package com.cursospringboot.init.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cursospringboot.init.exceptions.ProductoNoActualizableException;
import com.cursospringboot.init.exceptions.ProductoNoExistenteException;
@CrossOrigin("*")
@RestControllerAdvice
public class ExceptionsController {
	@ExceptionHandler(ProductoNoExistenteException.class)
	public ResponseEntity<String> noExistente(ProductoNoExistenteException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ProductoNoActualizableException.class)
	public ResponseEntity<String> noActualizable(ProductoNoActualizableException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
	}
}
