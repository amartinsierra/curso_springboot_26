package com.minsait.estudiantes.init;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.minsait.estudiantes.init.remote.interfaces.AlumnosRemoteInterface;

@SpringBootApplication
public class Application {
	@Value("${url.remote}")
	private String url;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public RestClient getClient() {
		return RestClient.create(url);
	}
	@Bean
    AlumnosRemoteInterface alumnosClient(RestClient restClient) {
        HttpServiceProxyFactory factory =
            HttpServiceProxyFactory
                .builderFor(
                    RestClientAdapter.create(restClient))
                .build();

        return factory.createClient(AlumnosRemoteInterface.class);
    }

	
}
