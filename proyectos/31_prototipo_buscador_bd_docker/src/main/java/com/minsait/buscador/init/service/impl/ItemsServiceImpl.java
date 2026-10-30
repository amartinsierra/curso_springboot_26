package com.minsait.buscador.init.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.minsait.buscador.init.entities.ItemEntity;
import com.minsait.buscador.init.exceptions.UrlRepetidaException;
import com.minsait.buscador.init.mappers.MapeadorObjetos;
import com.minsait.buscador.init.model.Item;
import com.minsait.buscador.init.repository.ItemsRepository;
import com.minsait.buscador.init.service.ItemsService;
@Service
public class ItemsServiceImpl implements ItemsService {
	
	ItemsRepository itemsRepository;
	MapeadorObjetos mapper;
	
	public ItemsServiceImpl(ItemsRepository itemsRepository, MapeadorObjetos mapper) {
		this.itemsRepository = itemsRepository;
		this.mapper = mapper;
	}
	@Override
	public List<Item> buscar(String tematica) {
		return itemsRepository.findByTematica(tematica) //List<ItemEntity>
				.stream()
				.map(e->mapper.entityToDto(e))
				.toList();
	}
	@Override
	public Optional<Item> alta(Item item) {
		Optional<ItemEntity> optEntity=itemsRepository.findFirstByUrl(item.url());
		if(optEntity.isPresent()) {
			throw new UrlRepetidaException();
		}
		ItemEntity entity= itemsRepository.save(mapper.dtoToEntity(item));
		return Optional.of(mapper.entityToDto(entity));
	}
	@Override
	public Optional<Item> eliminar(String url) {
		Optional<ItemEntity> optEntity=itemsRepository.findFirstByUrl(url);
		if(optEntity.isPresent()) {
			itemsRepository.deleteByUrl(url);
		}
		//transforma un optional de entidad en un optional de dto
		return optEntity.map(e->mapper.entityToDto(e));
	}

}
