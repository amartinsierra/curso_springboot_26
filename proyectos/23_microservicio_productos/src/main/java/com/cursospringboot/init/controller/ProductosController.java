package com.cursospringboot.init.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursospringboot.init.model.Producto;
import com.cursospringboot.init.service.ProductosService;
@CrossOrigin("*")
@RestController
public class ProductosController {
	ProductosService productosService;

	public ProductosController(ProductosService productosService) {
		this.productosService = productosService;
	}
	@GetMapping(value="productos")
	public  ResponseEntity<List<ProductoDto>> productos(){
		return ResponseEntity.ok(productosService.productos());
	}
	@PutMapping(value="productos/{codigo}/{unidades}")
	public ResponseEntity<Void> actualizarStock(@PathVariable int codigo,@PathVariable int unidades) {
		productosService.actualizarStock(codigo, unidades);
		return ResponseEntity.ok().build();
		
	}
	@GetMapping(value="productos/{codigo}")
	public ResponseEntity<String> precioProducto(@PathVariable int codigo) {
		return ResponseEntity.ok(String.valueOf(productosService.precioProducto(codigo)));
	}
}
