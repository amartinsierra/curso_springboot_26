package com.minsait.buscador.init.service;

import java.util.List;
import java.util.Optional;

import com.minsait.buscador.init.model.Item;

public interface ItemsService {
	List<Item> buscar(String tematica);
	Optional<Item> alta(Item item);
	Optional<Item> eliminar(String url);
}
