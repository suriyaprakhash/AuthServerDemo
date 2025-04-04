package com.suriyaprakhash.springboot_instrumentation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@SpringBootApplication
@Slf4j
public class ClientApplication {

	private RestClient restClient;

	public ClientApplication(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.baseUrl("http://localhost:8081").build();
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

//	public RestClient myRestClient() {
//		return RestClient.builder()
//				.baseUrl("http://localhost:8081")
//				.build();
//	}

	@GetMapping
	public String helloWorldInstrumentation() {
		log.info("First instrumentation logging message");
		return "Hello World!";
	}

	@GetMapping("/client")
	public String client() {
		log.info("Logging on client");
		return restClient.get().uri("/server").retrieve().body(String.class);
	}

}
