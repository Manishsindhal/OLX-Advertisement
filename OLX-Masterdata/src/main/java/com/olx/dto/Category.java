package com.olx.dto;

import io.swagger.annotations.ApiModelProperty;

public class Category {

	@ApiModelProperty("Unique identifier of the category")
	private int id;
	
	@ApiModelProperty("Name of the category")
	private String categoryName;
	
	@ApiModelProperty("description of the category")
	private String categoryDesc;
	
	public Category(int id, String categoryName, String categoryDesc) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
	} 
	
	public Category() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", categoryDesc=" + categoryDesc + "]";
	}

}
