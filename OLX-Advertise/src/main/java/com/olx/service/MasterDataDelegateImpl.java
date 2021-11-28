package com.olx.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@Service
public class MasterDataDelegateImpl implements MasterDataDelegate {

	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Override
	@CircuitBreaker(name = "CATEGORY-CIRCUIT-BREAKER", fallbackMethod = "fallBackGetAllCategories")
	public List<Map> getAllCategories() {
		// TODO Auto-generated method stub
		//TO call MasterData Microservices using RestTemplate
		
		List<Map> list = this.restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/category", List.class);
		return list;
	}
	
	public List<Map> fallBackGetAllCategories(Throwable ex) {
		System.out.println("MasterData Call Failed "+ ex);
		return null;
	}

	@Override
	@CircuitBreaker(name = "STATUS-CIRCUIT-BREAKER", fallbackMethod = "fallBackGetAllStatus")
	public List<Map> getAllStatus() {
		List<Map> list = this.restTemplate.getForObject("http://API-GATEWAY/olx/masterdata/advertise/status", List.class);
		return list;
	}
	
	public List<Map> fallBackGetAllStatus(Throwable ex) {
		System.out.println("MasterData Call Failed "+ ex);
		return null;
	}

}
