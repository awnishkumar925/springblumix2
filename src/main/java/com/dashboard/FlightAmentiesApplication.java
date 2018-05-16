package com.dashboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FlightAmentiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightAmentiesApplication.class, args);
	}
	

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@RestController
class AmentiesController {

    private final RestTemplate restTemplate;

    @Autowired
    public AmentiesController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/requestuser")
    public String getAmenties(HttpServletRequest request, HttpServletResponse response ) {
    	
    	response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    	HttpHeaders headers = new HttpHeaders();
    	
    	headers.add("Accept", "application/json");
    	
    	MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
    
    	
    	String url = "http://reqres.in/api/users/2";
    	HttpEntity<MultiValueMap<String, String>> requestAmenties = new HttpEntity<MultiValueMap<String, String>>(map, headers);
    	ResponseEntity<String> responseAmenmties = restTemplate.postForEntity( url, requestAmenties , String.class );
    	return responseAmenmties.getBody();
    }
	
}

