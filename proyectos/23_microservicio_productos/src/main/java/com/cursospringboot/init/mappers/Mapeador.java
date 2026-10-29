package com.cursospringboot.init.mappers;

import org.springframework.stereotype.Component;

import com.cursospringboot.init.dtos.ProductoDto;
import com.cursospringboot.init.model.Producto;

@Component
public class Mapeador {
	public ProductoDto toDto(Producto producto){
		return new ProductoDto(producto.getCodigoProducto(),producto.getProducto(),producto.getPrecioUnitario(),producto.getStock());
	}
	public Producto toEntity(ProductoDto producto){
		return new Producto(producto.codigoProducto(),producto.producto(),producto.precioUnitario(),producto.stock());
	}
}
