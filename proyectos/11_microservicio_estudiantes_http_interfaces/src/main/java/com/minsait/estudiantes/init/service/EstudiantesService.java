package com.minsait.estudiantes.init.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.minsait.estudiantes.init.exceptions.EstudianteRepetidoException;
import com.minsait.estudiantes.init.mappers.Mapeador;
import com.minsait.estudiantes.init.model.Alumno;
import com.minsait.estudiantes.init.model.Estudiante;
import com.minsait.estudiantes.init.remote.interfaces.AlumnosRemoteInterface;

@Service
public class EstudiantesService {
	AlumnosRemoteInterface alumnosRemote;
	Mapeador mapeador;
	public EstudiantesService(AlumnosRemoteInterface alumnosRemote, Mapeador mapeador) {
		this.alumnosRemote = alumnosRemote;
		this.mapeador = mapeador;
	}
	
	public List<Estudiante> estudiantesrangoCalificaciones(double min, double max){
		return alumnosRemote.alumnos().stream()
				.filter(a->a.nota()>=min&&a.nota()<=max)
				.map(mapeador::alumnoToEstudiante)
				.toList();
	}
	
	public void altaEstudiante(Estudiante estudiante) {
		try {
			alumnosRemote.altaAlumno(mapeador.estudianteToAlumno(estudiante));
		}
		catch(HttpClientErrorException ex) {
			throw new EstudianteRepetidoException();
		}
	}

}
