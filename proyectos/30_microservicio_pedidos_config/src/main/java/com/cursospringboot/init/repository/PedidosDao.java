package com.cursospringboot.init.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursospringboot.init.model.Pedido;

public interface PedidosDao extends JpaRepository<Pedido, Integer> {

}
