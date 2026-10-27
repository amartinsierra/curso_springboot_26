package com.minsait.buscador.init.mappers;

import org.springframework.stereotype.Component;

import com.minsait.buscador.init.entities.ItemEntity;
import com.minsait.buscador.init.model.Item;

@Component
public class MapeadorObjetos {
	public ItemEntity dtoToEntity(Item item) {
		return new ItemEntity(item.url(), item.tematica(), item.descripcion());
	}
	public Item entityToDto(ItemEntity item) {
		return new Item(item.getUrl(),item.getTematica(),item.getDescripcion());
	}
}
