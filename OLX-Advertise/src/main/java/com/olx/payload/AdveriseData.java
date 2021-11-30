package com.olx.payload;

import java.util.List;

import org.springframework.stereotype.Component;

import com.olx.dto.Advertisement;

@Component
public class AdveriseData {

	List<Advertisement> advertise;

	public AdveriseData(List<Advertisement> advertise) {
		super();
		this.advertise = advertise;
	}

	public List<Advertisement> getAdvertise() {
		return advertise;
	}

	public void setAdvertise(List<Advertisement> advertise) {
		this.advertise = advertise;
	}

	
	
	
}
