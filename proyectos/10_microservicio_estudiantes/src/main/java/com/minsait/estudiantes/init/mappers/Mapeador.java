package com.minsait.estudiantes.init.mappers;

import org.springframework.stereotype.Component;

import com.minsait.estudiantes.init.model.Alumno;
import com.minsait.estudiantes.init.model.Estudiante;

@Component
public class Mapeador {
	public Alumno estudianteToAlumno(Estudiante estudiante) {
		return new Alumno(0,estudiante.nombre(),estudiante.curso(),estudiante.email(),estudiante.calificacion());
		
	}
	public Estudiante alumnoToEstudiante(Alumno alumno) {
		return new Estudiante(alumno.nombre(),alumno.curso(),alumno.email(),alumno.nota());
		
	}
}
