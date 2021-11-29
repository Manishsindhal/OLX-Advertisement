package com.olx.utility;

import java.util.List;

import org.springframework.stereotype.Component;

import com.olx.dto.Category;
import com.olx.dto.Status;

@Component
public class MasterdataCategory {

	List<Category> category;

	public MasterdataCategory(List<Category> category) {
		super();
		this.category = category;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}
	
}
