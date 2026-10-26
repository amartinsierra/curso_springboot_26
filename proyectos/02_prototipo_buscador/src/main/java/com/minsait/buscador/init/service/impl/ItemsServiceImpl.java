package com.minsait.buscador.init.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.minsait.buscador.init.model.Item;
import com.minsait.buscador.init.service.ItemsService;
@Service
public class ItemsServiceImpl implements ItemsService {
	static List<Item> direcciones=new ArrayList<>(Arrays.asList(new Item("http://www.amazon.es","libros","web de libros y más cosas"),
			new Item("http://www.fnac.es","libros","libreria completa"),
			new Item("http://www.travel.es","viajes","viajes por el mundo"),
			new Item("http://www.game.es","juegos","el mundo del juego"),
			new Item("http://www.fly.com","viajes","vuelos a todos los destinos"),
			new Item("http://www.casadellibro.es","libros","libros de todos los temas")
			));
	@Override
	public List<Item> buscar(String tematica) {
		return direcciones.stream()
				.filter(item -> item.tematica().contains(tematica))
				.toList();
	}
	@Override
	public Optional<Item> alta(Item item) {
		//da de alta si no existe esa URL
		if(direcciones.stream().anyMatch(it->it.url().equals(item.url()))) {
			return Optional.empty();
		}
		direcciones.add(item);
		return Optional.of(item);
	}
	@Override
	public Optional<Item> eliminar(String url) {
		Optional<Item> obj= direcciones.stream().filter(it->it.url().equals(url)).findFirst();
		if(obj.isPresent()) {
			direcciones.removeIf(it->it.url().equals(url));
		}
		return obj;
	}

}
