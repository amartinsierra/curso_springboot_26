package com.minsait.buscador.init.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.buscador.init.model.Item;
import com.minsait.buscador.init.service.ItemsService;

@RestController
@RequestMapping("items")
public class ItemsController {
	/*@Autowired
	ItemsService itemsService;*/
	
	ItemsService itemsService;
	public ItemsController(ItemsService itemsService) {
		this.itemsService=itemsService;
	}
	
	@GetMapping()
	public ResponseEntity<List<Item>> buscar(@RequestParam String tematica){
		//return new ResponseEntity<>(itemsService.buscar(tematica),HttpStatus.OK);
		return ResponseEntity.ok(itemsService.buscar(tematica));
	}
	@PostMapping
	public ResponseEntity<Void> nuevoItem(@RequestBody Item item) {
		//return new ResponseEntity<>(HttpStatus.CREATED);
		itemsService.alta(item);
		return ResponseEntity.status(HttpStatus.CREATED).build();
				
	}
	@DeleteMapping
	public ResponseEntity<Item> eliminarItem(@RequestParam String url) {
		return ResponseEntity.ok(itemsService.eliminar(url).orElse(null));
	}
}
