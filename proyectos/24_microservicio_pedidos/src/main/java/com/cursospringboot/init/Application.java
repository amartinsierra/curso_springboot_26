package com.cursospringboot.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	//**************configuración RestClient
	@Bean
    @LoadBalanced
    public RestClient.Builder loadBalanced(){
        return RestClient.builder();
    }
    @Bean
    @Primary
    public RestClient.Builder builder(){
        return RestClient.builder();
    }
}
