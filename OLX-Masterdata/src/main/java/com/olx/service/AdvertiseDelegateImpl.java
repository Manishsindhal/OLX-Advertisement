package com.olx.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AdvertiseDelegateImpl implements AdvertiseDelegate {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Override
	public Collection<Map> getAllAdvertisement() {
		// TODO Auto-generated method stub
		Collection<Map> list = this.restTemplate.getForObject("http://localhost:9002/user/advertise", List.class);
		return list;
	}

	
}
