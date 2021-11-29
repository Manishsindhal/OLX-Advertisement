package com.olx.service;


import org.springframework.http.ResponseEntity;

import com.olx.utility.MasterdataCategory;
import com.olx.utility.MasterdataStatus;

public interface MasterDataService {

	public ResponseEntity<MasterdataCategory> getAllCategories();
	
	public ResponseEntity<MasterdataStatus> getAllStatus();
	
}
