package com.cursospringboot.init.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPedido;
	private Integer codigoProducto;
	private Integer unidades;
	private Double total;
	private LocalDateTime fechaPedido;
	public Pedido(Integer idPedido, Integer codigoProducto, Integer unidades, Double total, LocalDateTime fechaPedido) {
		this.idPedido = idPedido;
		this.codigoProducto = codigoProducto;
		this.unidades = unidades;
		this.total = total;
		this.fechaPedido = fechaPedido;
	}
	public Pedido() {
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Integer getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(Integer codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public Integer getUnidades() {
		return unidades;
	}
	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public LocalDateTime getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	
	
}