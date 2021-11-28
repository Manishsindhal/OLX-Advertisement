package com.olx.dto;

import io.swagger.annotations.ApiModelProperty;

public class Status {

	@ApiModelProperty("Unique identifier of the status")
	private int id;
	
	@ApiModelProperty("Name of the status")
	private String status;
	
	public Status(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	
	public Status() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", status=" + status + "]";
	}
	
	
	
}
