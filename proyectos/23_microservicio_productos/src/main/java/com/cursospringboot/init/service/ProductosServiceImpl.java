package com.cursospringboot.init.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cursospringboot.init.exceptions.ProductoNoActualizableException;
import com.cursospringboot.init.exceptions.ProductoNoExistenteException;
import com.cursospringboot.init.model.Producto;
import com.cursospringboot.init.repository.ProductosDao;
@Service
public class ProductosServiceImpl implements ProductosService {
	ProductosDao productosDao;
	
	public ProductosServiceImpl(ProductosDao productosDao) {
		this.productosDao = productosDao;
	}

	@Override
	public List<ProductoDto> productos() {
		//******************
	}

	@Override
	public double precioProducto(int codigoProducto) throws ProductoNoExistenteException {
		Optional<Producto> opProducto=productosDao.findById(codigoProducto);
		if(opProducto.isPresent()) {
			return opProducto.get().getPrecioUnitario();
		}
		throw new ProductoNoExistenteException("Producto no existe con ese identificador");
	}

	@Override
	public void actualizarStock(int codigoProducto, int unidades) throws ProductoNoActualizableException {
		Optional<Producto> opProducto=productosDao.findById(codigoProducto);
		if(opProducto.isPresent()&&unidades<=opProducto.get().getStock()) {
			//****************
		}
		throw new ProductoNoActualizableException("No se pudo actualizar stock");

	}

}
