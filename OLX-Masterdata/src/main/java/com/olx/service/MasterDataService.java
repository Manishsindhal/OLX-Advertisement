package com.olx.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.olx.dto.Category;
import com.olx.dto.Status;

public interface MasterDataService {

	public ResponseEntity<List<Category>> getAllCategories();
	
	public ResponseEntity<List<Status>> getAllStatus();
	
}
