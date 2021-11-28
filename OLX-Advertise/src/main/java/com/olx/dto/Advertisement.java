package com.olx.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Advertise Model")
public class Advertisement {

	@ApiModelProperty("Unique identifier of the Advertise")
	private int id;
	
	@ApiModelProperty("Title Of Advertise")
	private String title;
	
	@ApiModelProperty("Category Of Advertise")
	private int category;
	
	@ApiModelProperty("Status Of Advertise")
	private int status;
	
	@ApiModelProperty("Price Of Advertise")
	private double price;
	
	@ApiModelProperty("Description Of Advertise")
	private String description;
	
	@ApiModelProperty("Created Date Of Advertise")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDate createdDate;
	
	@ApiModelProperty("Modified Date Of Advertise")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDate modifiedDate;
	
	@ApiModelProperty("Active status of Advertise")
	private int active;
	
	@ApiModelProperty("Name of the user who posted the Advertise")
	private String postedBy;
	
	@ApiModelProperty("Username of the user")
	private String username;

	public Advertisement(int id, String title, int category, int status, double price, String description,
			LocalDate createdDate, LocalDate modifiedDate, int active, String postedBy, String username) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.status = status;
		this.price = price;
		this.description = description;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.active = active;
		this.postedBy = postedBy;
		this.username = username;
	}
	
	public Advertisement() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", title=" + title + ", category=" + category + ", status=" + status
				+ ", price=" + price + ", description=" + description + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", active=" + active + ", postedBy=" + postedBy + ", username="
				+ username + "]";
	}
	
	
}
