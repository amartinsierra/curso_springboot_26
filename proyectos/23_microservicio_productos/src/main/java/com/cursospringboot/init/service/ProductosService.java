package com.cursospringboot.init.service;

import java.util.List;

import com.cursospringboot.init.exceptions.ProductoNoActualizableException;
import com.cursospringboot.init.exceptions.ProductoNoExistenteException;
import com.cursospringboot.init.model.Producto;

public interface ProductosService {
	List<ProductoDto> productos();
	double precioProducto(int codigoProducto) throws ProductoNoExistenteException;
	void actualizarStock(int codigoProducto, int unidades) throws ProductoNoActualizableException;
}
