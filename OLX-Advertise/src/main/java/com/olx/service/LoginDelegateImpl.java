package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginDelegateImpl implements LoginDelegate {

	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestAuthTemplate() {
		return new RestTemplate();
	}

	@Override
	//@CircuitBreaker(name = "VALIDATE-TOKEN-CIRCUIT-BREAKER", fallbackMethod = "fallBackIsValidateToken")
	public ResponseEntity<Boolean> validateToken(String authToken) {
		// TODO Auto-generated method stub
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<Boolean> authTokenValdidate = restTemplate.exchange("http://API-GATEWAY/olx/user/user/validate/token", HttpMethod.GET, entity, Boolean.class);
		return authTokenValdidate;
	}
	
//	public boolean fallBackIsValidateToken(String authToken, Throwable ex) {
//		System.out.println("AuthToken Call Failed "+ ex);
//		return false;
//	}
}
