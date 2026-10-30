package com.cursospring.init.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.cursospring.init.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Value("${security.db.driver}")
	private String driver;
	@Value("${security.db.url}")
	private String url;
	@Value("${security.db.user}")
	private String user;
	@Value("${security.db.password}")
	private String pass;
	AuthenticationManager auth;
	@Value("${jwt.properties.clave}")
	String clave; 
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration conf) throws Exception{
        auth=conf.getAuthenticationManager();
        return auth;
    }

	@Bean
	public JdbcUserDetailsManager  usersDetailsBd() throws Exception {
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(pass);
		JdbcUserDetailsManager jdbcDetails=new JdbcUserDetailsManager(ds);
		
		jdbcDetails.setUsersByUsernameQuery("select user, pwd, enabled"
           	+ " from users where user=?");
		jdbcDetails.setAuthoritiesByUsernameQuery("select user, rol "
           	+ "from roles where user=?");
		return jdbcDetails;
	}
	//codificador bcrypt
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	//restricciones de acceso a recursos
		@Bean
		public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception  {		
			http.csrf(cr->cr.disable())
			.authorizeHttpRequests(aut->
					aut.requestMatchers(HttpMethod.POST,"/alumnos").hasRole("ADMINS")
					.requestMatchers(HttpMethod.DELETE,"/alumnos").hasAnyRole("ADMINS","OPERATORS")
					.requestMatchers(HttpMethod.GET,"/alumnos").authenticated()
					.anyRequest().permitAll())
			.addFilter(new JwtAuthenticationFilter(auth,clave));
			return http.build();
			
		}
}
