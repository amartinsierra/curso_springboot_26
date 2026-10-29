package com.cursospringboot.init.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.cursospringboot.init.model.Pedido;
import com.cursospringboot.init.repository.PedidosDao;
@Service
public class PedidosServiceImpl implements PedidosService {
	RestClient restClient;
	PedidosDao pedidosDao;
	@Value("${remote.url}")
	String url;
	
	//************constructor
	public PedidosServiceImpl(@LoadBalanced RestClient.Builder builder, PedidosDao pedidosDao) {
		this.restClient = builder.build();
		this.pedidosDao = pedidosDao;
	}
	@Override
	public List<Pedido> pedidos() {
		return pedidosDao.findAll();
	}

	@Override
	public boolean altaPedido(Pedido pedido) {
		pedido.setFechaPedido(LocalDateTime.now());
		try {
			
			pedido.setTotal(pedido.getUnidades()*precioProducto(pedido.getCodigoProducto()));
			pedidosDao.save(pedido);
			actualizarProducto(pedido.getCodigoProducto(), pedido.getUnidades());
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	private double precioProducto(int codigoProducto) {
		return Double.parseDouble(restClient.get()
				.uri(url+codigoProducto)
				.retrieve()
				.body(String.class));
		
	}
	private void actualizarProducto(int codigoProducto, int unidades) {
		//******************
		restClient.put()
		.uri(url+codigoProducto+"/"+unidades)
		.retrieve()
		.toBodilessEntity();
	}
}
