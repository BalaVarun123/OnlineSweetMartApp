package com.cg.osm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication

public class OnlineSweetMartAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineSweetMartAppApplication.class, args);
	}
	
	@Bean
	public  RestTemplate  getRestTemplate() {
		
		return new RestTemplate();
		
		
	}

}
