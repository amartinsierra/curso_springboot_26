package com.minsait.estudiantes.init.remote.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.minsait.estudiantes.init.model.Alumno;

public interface AlumnosRemoteInterface {
	@GetExchange
	List<Alumno> alumnos();
	@PostExchange
	void altaAlumno(@RequestBody Alumno alumno);
}
