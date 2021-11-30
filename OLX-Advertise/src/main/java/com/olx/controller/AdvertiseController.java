package com.olx.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.olx.dto.Advertisement;
import com.olx.exception.InvalidAdvertiseIdExeption;
import com.olx.payload.AdveriseData;
import com.olx.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("olx/olxadvertise")
public class AdvertiseController {

	@Autowired
	AdvertiseService advertiseService;
	
	@PostMapping(value = "/advertise", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Create New Advertise")
	public Advertisement CreateNewAdvertise(@RequestHeader("auth-token") String authToken, @RequestBody Advertisement advertise) {
		
		return advertiseService.CreateNewAdvertise(authToken, advertise);
	}

	@PutMapping(value = "/advertise/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Update Advertise")
	public Advertisement updateAdvertiseById(@RequestHeader("auth-token") String authToken, @PathVariable("id") int advertiseId, @RequestBody Advertisement newAdvertise) {
		
		return advertiseService.updateAdvertiseById(authToken, advertiseId, newAdvertise);
	}

	@GetMapping(value = "/user/advertise", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Returns All Advertisement")
	public ResponseEntity<AdveriseData> getAllAdvertisement(@RequestHeader("auth-token") String authToken) {
		
		return advertiseService.getAllAdvertisement(authToken);
	}

	@GetMapping(value = "/user/advertise/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Returns Advertise By Id")
	public Advertisement getAdvertiseById(@RequestHeader("auth-token") String authToken, @PathVariable("id") int advertiseId) {
		// return advertiseMap.get(advertiseId);
		return advertiseService.getAdvertiseById(authToken, advertiseId);
	}

	@DeleteMapping(value = "/user/advertise/{id}")
	@ApiOperation(value = "Delete Advertise By Id")
	public boolean deleteAdvertiseById(@RequestHeader("auth-token") String authToken, @PathVariable("id") int advertiseId) {
		
		return advertiseService.deleteAdvertiseById(authToken, advertiseId);
	}

	@GetMapping(value = "/advertise/search/filtercriteria", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Search Advertise By Filter Criteria")
	public @ResponseBody ResponseEntity<AdveriseData> searchAdvertiseByFiltercriteria(@RequestParam("searchText") String searchText,
			@RequestParam(name = "category", required = false) int categoryId,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "dateCondition", required = false) String dateCondition,
			@RequestParam(name = "onDate", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate onDate,
			@RequestParam(name = "fromDate", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate fromDate,
			@RequestParam(name = "toDate", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate toDate,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "startIndex", required = false) int startIndex,
			@RequestParam(name = "records", required = false) int records) {

		return advertiseService.searchAdvertiseByFiltercriteria(searchText, categoryId, username, dateCondition, onDate,
				fromDate, toDate, sortBy, startIndex, records);
	}

	@GetMapping(value = "/advertise/search", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Search Advertise By SearchText(Keyword)")
	public @ResponseBody ResponseEntity<AdveriseData> searchAdvertiseByText(@RequestParam("searchText") String searchText) {

		return advertiseService.searchAdvertiseByText(searchText);
	}

	@GetMapping(value = "/advertise/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Returns Advertise Details By Id")
	public Advertisement getAdvertiseDetailById(@RequestHeader("auth-token") String authToken, @PathVariable("id") int advertiseId) {

		return advertiseService.getAdvertiseDetailById(authToken, advertiseId);
	}

}
