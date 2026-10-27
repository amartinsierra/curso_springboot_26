package com.minsait.buscador.init.controller;

import java.util.List;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("items")
public class ItemsController {
	/*@Autowired
	ItemsService itemsService;*/
	
	ItemsService itemsService;
	public ItemsController(ItemsService itemsService) {
		this.itemsService=itemsService;
	}
	@Operation(summary = "Lista de items por temática",
            description = "A partir de la temática recibida como prámetro, devuelve los items correspondientes")

	@GetMapping()
	public ResponseEntity<List<Item>> buscar(@Parameter(description = "Nombre de la temática de búsqueda")@RequestParam String tematica){
		//return new ResponseEntity<>(itemsService.buscar(tematica),HttpStatus.OK);
		return ResponseEntity.ok(itemsService.buscar(tematica));
	}
	@ApiResponses({
        @ApiResponse(responseCode = "201", description = "Alumno creado correctamente", content = @Content),
        @ApiResponse(responseCode = "409", description = "No se pudo crear el alumno (conflicto)", content = @Content)
    })

	@PostMapping
	public ResponseEntity<Void> nuevoItem(@Valid @RequestBody Item item) {
		//return new ResponseEntity<>(HttpStatus.CREATED);
		itemsService.alta(item);
		return ResponseEntity.status(HttpStatus.CREATED).build();
				
	}
	@DeleteMapping
	public ResponseEntity<Item> eliminarItem(@RequestParam String url) {
		return ResponseEntity.ok(itemsService.eliminar(url).orElse(null));
	}
}
