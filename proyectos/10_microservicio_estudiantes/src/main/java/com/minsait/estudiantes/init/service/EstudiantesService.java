package com.minsait.estudiantes.init.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.minsait.estudiantes.init.exceptions.EstudianteRepetidoException;
import com.minsait.estudiantes.init.mappers.Mapeador;
import com.minsait.estudiantes.init.model.Alumno;
import com.minsait.estudiantes.init.model.Estudiante;

@Service
public class EstudiantesService {
	RestClient restClient;
	Mapeador mapeador;
	private String url="http://localhost:8001/alumnos";
	public EstudiantesService(RestClient restClient, Mapeador mapeador) {
		this.restClient = restClient;
		this.mapeador = mapeador;
	}
	
	public List<Estudiante> estudiantesrangoCalificaciones(double min, double max){
		return Arrays.stream(restClient.get()
				.uri(url)
				.retrieve()
				.body(Alumno[].class))
				.filter(a->a.nota()>=min&&a.nota()<=max)
				.map(mapeador::alumnoToEstudiante)
				.toList();
	}
	
	public void altaEstudiante(Estudiante estudiante) {
		try {
			restClient.post()
			.uri(url)
			.contentType(MediaType.APPLICATION_JSON)
			.body(mapeador.estudianteToAlumno(estudiante))
			.retrieve()
			.toBodilessEntity();
		}
		catch(HttpClientErrorException ex) {
			throw new EstudianteRepetidoException();
		}
	}

}
