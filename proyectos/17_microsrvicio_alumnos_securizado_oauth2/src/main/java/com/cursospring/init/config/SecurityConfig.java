package com.cursospring.init.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.cursospring.init.converters.JwtAuthConverter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
    private  JwtAuthConverter jwtAuthConverter;  

	//restricciones de acceso a recursos
		@Bean
		public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception  {		
			http.csrf(cr->cr.disable())
			.authorizeHttpRequests(aut->
					aut.requestMatchers(HttpMethod.POST,"/alumnos").hasRole("ADMINS")
					.requestMatchers(HttpMethod.DELETE,"/alumnos").hasAnyRole("ADMINS","OPERATORS")
					.requestMatchers(HttpMethod.GET,"/alumnos").authenticated()
					.anyRequest().permitAll())
	        .oauth2ResourceServer(oauth2ResourceServer->
                oauth2ResourceServer.jwt(jwt->jwt
                .jwtAuthenticationConverter(jwtAuthConverter)))                                             
                .sessionManagement(sessionManagement->
                      sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));    
			return http.build();
			
		}
}
