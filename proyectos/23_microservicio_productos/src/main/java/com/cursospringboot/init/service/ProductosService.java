package com.cursospringboot.init.service;

import java.util.List;

import com.cursospringboot.init.dtos.ProductoDto;
import com.cursospringboot.init.exceptions.ProductoNoActualizableException;
import com.cursospringboot.init.exceptions.ProductoNoExistenteException;

public interface ProductosService {
	List<ProductoDto> productos();
	double precioProducto(int codigoProducto) throws ProductoNoExistenteException;
	void actualizarStock(int codigoProducto, int unidades) throws ProductoNoActualizableException;
}
