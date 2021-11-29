package com.olx.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = ("tb_advertise"))
public class AdvertisementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "category")
	private int category;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "createdDate", nullable = true)
	private LocalDate createdDate;
	
	@Column(name = "modifiedDate", nullable = true)
	private LocalDate modifiedDate;
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "postedBy")
	private String postedBy;
	
	@Column(name = "username")
	private String username;

	public AdvertisementEntity(int id, String title, int category, int status, double price, String description,
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
	
	public AdvertisementEntity(String title, int category, int status, double price, String description,
			LocalDate createdDate, LocalDate modifiedDate, int active, String postedBy, String username) {
		super();
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
	
	public AdvertisementEntity() {}

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
		return "AdvertisementEntity [id=" + id + ", title=" + title + ", category=" + category + ", status=" + status
				+ ", price=" + price + ", description=" + description + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", active=" + active + ", postedBy=" + postedBy + ", username="
				+ username + "]";
	}
	
	
}
