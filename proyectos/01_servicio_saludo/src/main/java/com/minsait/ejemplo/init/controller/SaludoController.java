package com.minsait.ejemplo.init.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.ejemplo.init.model.Ficha;

@RestController
@RequestMapping("saludar")
public class SaludoController {
	@GetMapping
	public String getSaludo() {
		return "Bienvenido a REST";
	}
	@GetMapping("{name}")
	public String getSaludo(@PathVariable String name) {
		return "Bienvenido a REST "+name;
	}
	
	@GetMapping("completo")
	public String getSaludo(@RequestParam String name,@RequestParam int age) {
		return "Bienvenido a REST te llamas"+name+" y tienes "+age+" años";
	}
	@GetMapping("ficha/{code}")
	public Ficha info(@PathVariable int code) {
		return new Ficha("prueba","prueba@gmail.com",code);
	}
	@PostMapping()
	public void altaFicha(@RequestBody Ficha ficha) {
		System.out.println("Dado de alta la ficha de "+ficha.name());
	}
}
