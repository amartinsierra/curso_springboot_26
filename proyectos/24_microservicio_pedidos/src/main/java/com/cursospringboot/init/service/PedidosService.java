package com.cursospringboot.init.service;

import java.util.List;

import com.cursospringboot.init.model.Pedido;

public interface PedidosService {
	List<Pedido> pedidos();
	boolean altaPedido(Pedido pedido);
}
