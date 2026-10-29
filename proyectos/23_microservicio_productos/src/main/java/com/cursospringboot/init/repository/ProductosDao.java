package com.cursospringboot.init.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursospringboot.init.model.Producto;

public interface ProductosDao extends JpaRepository<Producto, Integer> {
	//**************falta un método
}
