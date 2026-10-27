package com.minsait.buscador.init.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.minsait.buscador.init.entities.ItemEntity;

public interface ItemsRepository extends JpaRepository<ItemEntity, Integer> {
	//necesitamos save() que es heredado de JpaRepository
	List<ItemEntity> findByTematica(String tematica);
	
	@Transactional
	@Modifying
	void deleteByUrl(String url);
	
	Optional<ItemEntity> findFirstByUrl(String url);
}
