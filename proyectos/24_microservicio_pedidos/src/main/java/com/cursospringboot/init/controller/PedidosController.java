package com.cursospringboot.init.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursospringboot.init.model.Pedido;
import com.cursospringboot.init.service.PedidosService;
@CrossOrigin("*")
@RestController
public class PedidosController {
	PedidosService pedidosService;

	public PedidosController(PedidosService pedidosService) {
		this.pedidosService = pedidosService;
	}
	@GetMapping(value="pedidos")
	public ResponseEntity<List<Pedido>> pedidos(){
		return ResponseEntity.ok(pedidosService.pedidos());
	}
	@PostMapping(value="pedidos")
	public ResponseEntity<Void> altaPedido(@RequestBody Pedido pedido) {
		//*****si no se pudo dar de alta, conflicto
	}
}
