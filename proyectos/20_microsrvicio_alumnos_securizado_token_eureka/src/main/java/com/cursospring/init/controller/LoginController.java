package com.cursospring.init.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursospring.init.dtos.CredentialsDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
public class LoginController {
	@Value("${jwt.properties.timeout}")
	long timeOut;
	@Value("${jwt.properties.clave}")
	String clave; 
	
	AuthenticationManager authManager;
	public LoginController(AuthenticationManager authManager) {
		this.authManager=authManager;
	}
	
	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody CredentialsDto dto) {
		try {
			Authentication autentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.user(),dto.password()));
			return new ResponseEntity<>(getToken(autentication),HttpStatus.OK);
		}catch(AuthenticationException ex) {
			ex.printStackTrace();
			//si el usuario no es válido, enviamos el código de estado 401
			return new ResponseEntity<>("No autorizado",HttpStatus.UNAUTHORIZED);
		}
	}
	private String getToken(Authentication autentication) {
		
		//en el body del token se incluye el usuario 
		//y los roles a los que pertenece, además
		//de la fecha de caducidad y los datos de la firma
		String token = Jwts.builder()
				.setIssuedAt(new Date()) //fecha creación
				.setSubject(autentication.getName()) //usuario
				.claim("authorities",autentication.getAuthorities().stream() //roles
								.map(GrantedAuthority::getAuthority)
								.toList())
				.setExpiration(new Date(System.currentTimeMillis() + timeOut)) //fecha caducidad
				.signWith(Keys.hmacShaKeyFor(clave.getBytes()))//clave y algoritmo para firma				
				.compact(); //generación del token
		return token;
	}
}
