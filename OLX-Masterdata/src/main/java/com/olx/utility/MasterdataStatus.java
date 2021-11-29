package com.olx.utility;

import java.util.List;

import org.springframework.stereotype.Component;

import com.olx.dto.Status;

@Component
public class MasterdataStatus {

	List<Status> status;

	public MasterdataStatus(List<Status> status) {
		super();
		this.status = status;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}
	
	
}
