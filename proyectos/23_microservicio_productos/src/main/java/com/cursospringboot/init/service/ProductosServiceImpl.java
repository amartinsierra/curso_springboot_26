package com.cursospringboot.init.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cursospringboot.init.dtos.ProductoDto;
import com.cursospringboot.init.exceptions.ProductoNoActualizableException;
import com.cursospringboot.init.exceptions.ProductoNoExistenteException;
import com.cursospringboot.init.mappers.Mapeador;
import com.cursospringboot.init.model.Producto;
import com.cursospringboot.init.repository.ProductosDao;
@Service
public class ProductosServiceImpl implements ProductosService {
	ProductosDao productosDao;
	Mapeador mapeador;
	

	public ProductosServiceImpl(ProductosDao productosDao, Mapeador mapeador) {
		this.productosDao = productosDao;
		this.mapeador = mapeador;
	}

	@Override
	public List<ProductoDto> productos() {
		return productosDao.findAll().stream()
				.map(mapeador::toDto)
				.toList();
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
			Producto p=opProducto.get();
			p.setStock(p.getStock()-unidades);
			productosDao.save(p);
			return;
		}
		throw new ProductoNoActualizableException("No se pudo actualizar stock");

	}

}
