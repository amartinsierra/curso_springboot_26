package com.cursospring.init.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursospring.init.dtos.AlumnoDto;
import com.cursospring.init.service.AlumnosService;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RequestMapping("alumnos")
@RestController
public class AlumnosController {
	
	AlumnosService alumnosService;
	Counter contadorEntradas;
	public AlumnosController(AlumnosService alumnosService,MeterRegistry registry) {
		this.alumnosService = alumnosService;
		this.contadorEntradas = Counter.builder("alumnos.consultas")
                .description("Número de consultas a la lista de alumnos")
                .register(registry);

	}
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlumnoDto> alta(@RequestBody AlumnoDto alumno) {
		Optional<AlumnoDto> alumnoOp= alumnosService.altaAlumno(alumno);
		if(alumnoOp.isPresent()) {
			return new ResponseEntity<>(alumnoOp.get(),HttpStatus.CREATED);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> cursos() {
		contadorEntradas.increment();
		return  new ResponseEntity<>(alumnosService.cursos(),HttpStatus.OK);	
	}
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoDto>> alumnos() {
		contadorEntradas.increment();
		return new ResponseEntity<>(alumnosService.alumnos(),HttpStatus.OK);
		
	}
	@GetMapping(value="por-curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AlumnoDto>> alumnoscurso(@RequestParam String curso) {
		return new ResponseEntity<>(alumnosService.alumnosCurso(curso),HttpStatus.OK);
	}
	@DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlumnoDto> eliminar(@RequestParam String email){		
		return new ResponseEntity<>(alumnosService.eliminarAlumno(email).orElse(null),HttpStatus.OK);
	}
}
