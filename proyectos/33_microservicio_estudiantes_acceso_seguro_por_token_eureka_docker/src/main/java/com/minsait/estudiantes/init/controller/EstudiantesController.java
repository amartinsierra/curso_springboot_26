package com.minsait.estudiantes.init.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.estudiantes.init.model.Estudiante;
import com.minsait.estudiantes.init.service.EstudiantesService;

@RestController
public class EstudiantesController {
	EstudiantesService estudiantesService;

	public EstudiantesController(EstudiantesService estudiantesService) {
		this.estudiantesService = estudiantesService;
	}
	@GetMapping("estudiantes")
	public ResponseEntity<List<Estudiante>> estudiantesPorCalificacion(double min, double max){
		return ResponseEntity.ok(estudiantesService.estudiantesrangoCalificaciones(min, max));
	}
	@PostMapping("estudiantes")
	public ResponseEntity<Void> altaEstudiante(@RequestBody Estudiante estudiante){
		estudiantesService.altaEstudiante(estudiante);
		return ResponseEntity.ok().build();
	}
}
