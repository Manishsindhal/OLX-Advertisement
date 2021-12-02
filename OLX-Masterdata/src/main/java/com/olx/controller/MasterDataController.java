package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.Category;
import com.olx.dto.Status;
import com.olx.service.MasterDataService;
import com.olx.utility.MasterdataCategory;
import com.olx.utility.MasterdataStatus;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("olx/masterdata")
public class MasterDataController {

	@Autowired
	MasterDataService masterDataService;
	
	//This GET method is responsible for get all categories 
	@GetMapping(value="/advertise/category", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Returns All Categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		
		return masterDataService.getAllCategories();  
	}
		
	//This GET method is responsible for get all status
	@GetMapping(value="/advertise/status", produces={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Returns All Status")
	public ResponseEntity<List<Status>> getAllStatus() {
		
		return masterDataService.getAllStatus(); 
	}
}
