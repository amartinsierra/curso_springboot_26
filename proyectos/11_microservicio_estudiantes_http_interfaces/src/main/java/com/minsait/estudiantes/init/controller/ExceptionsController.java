package com.minsait.estudiantes.init.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.minsait.estudiantes.init.exceptions.EstudianteRepetidoException;

@RestControllerAdvice
public class ExceptionsController {
	@ExceptionHandler(EstudianteRepetidoException.class)
	public ResponseEntity<Void> exceptionUrl(EstudianteRepetidoException ex){
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
}
