package com.minsait.estudiantes.init.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.minsait.estudiantes.init.exceptions.EstudianteRepetidoException;
import com.minsait.estudiantes.init.mappers.Mapeador;
import com.minsait.estudiantes.init.model.Alumno;
import com.minsait.estudiantes.init.model.Estudiante;
import com.minsait.estudiantes.init.model.TokenResponse;

@Service
public class EstudiantesService implements InitializingBean{
	RestClient restClient;
	Mapeador mapeador;
	@Value("${url.remote}")
	private String url;
	@Value("${url.remote.user}")
	private String userRemote;
	@Value("${url.remote.password}")
	private String passRemote;
	
	@Value("${auth.url}")
	private String urlKeycloak;
	@Value("${auth.clientId}")
	private String clientId;
	@Value("${auth.grantType}")
	private String grantType;
	
	private TokenResponse tokenResponse;
	public EstudiantesService(RestClient restClient, Mapeador mapeador) {
		this.restClient = restClient;
		this.mapeador = mapeador;
	}
	
	public List<Estudiante> estudiantesrangoCalificaciones(double min, double max){
		return Arrays.stream(restClient.get()
				.uri(url)
				.header("Authorization", "Bearer "+tokenResponse.access_token())
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
			.header("Authorization", "Bearer "+tokenResponse.access_token())
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
		MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("username", userRemote);
		params.add("password", passRemote);
		params.add("grant_type", grantType);
		tokenResponse=restClient.post()
			  .uri(urlKeycloak)
			  .contentType(MediaType.APPLICATION_FORM_URLENCODED)
			  .body(params)
			  .retrieve()
			  .body(TokenResponse.class);
		System.out.println(tokenResponse.access_token());
	}

}
