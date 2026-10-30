package com.minsait.buscador.init.exceptions;

public class UrlRepetidaException extends RuntimeException{
	public UrlRepetidaException() {
		super("No se admiten URLs duplicadas");
	}
}
