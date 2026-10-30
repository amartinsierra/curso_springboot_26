package com.minsait.estudiantes.init.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.minsait.estudiantes.init.exceptions.EstudianteRepetidoException;
import com.minsait.estudiantes.init.mappers.Mapeador;
import com.minsait.estudiantes.init.model.Alumno;
import com.minsait.estudiantes.init.model.Credentials;
import com.minsait.estudiantes.init.model.Estudiante;

@Service
public class EstudiantesService implements InitializingBean{
	RestClient restClient;
	Mapeador mapeador;
	@Value("${url.remote.url}")
	private String url;
	@Value("${url.remote.user}")
	private String userRemote;
	@Value("${url.remote.password}")
	private String passRemote;
	@Value("${url.remote.login}")
	private String urlLogin;
	private String token;
	public EstudiantesService(@Qualifier("loadBalanced") RestClient.Builder builder, Mapeador mapeador) {
		this.restClient = builder.build();
		this.mapeador = mapeador;
	}
	
	public List<Estudiante> estudiantesrangoCalificaciones(double min, double max){
		return Arrays.stream(restClient.get()
				.uri(url)
				.header("Authorization", "Bearer "+token)
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
			.header("Authorization", "Bearer "+token)
			.retrieve()
			.toBodilessEntity();
		}
		catch(HttpClientErrorException ex) {
			throw new EstudianteRepetidoException();
		}
	}
	
	private String getBase64(String user, String pass) {
		return Base64.getEncoder().encodeToString((user+":"+pass).getBytes());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		token=restClient.post()
			  .uri(urlLogin)
			  .contentType(MediaType.APPLICATION_JSON)
			  .body(new Credentials(userRemote,passRemote))
			  .retrieve()
			  .body(String.class);
		
	}

}
