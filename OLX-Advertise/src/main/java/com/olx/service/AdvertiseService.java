package com.olx.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.olx.dto.Advertisement;
import com.olx.utility.AdveriseData;


public interface AdvertiseService {

	public Advertisement CreateNewAdvertise(@RequestHeader("auth-token") String authToken,
			@RequestBody Advertisement advertise);

	public Advertisement updateAdvertiseById(@RequestHeader("auth-token") String authToken,
			@PathVariable("id") int advertiseId, @RequestBody Advertisement newAdvertise);

	public ResponseEntity<AdveriseData> getAllAdvertisement(@RequestHeader("auth-token") String authToken);

	public Advertisement getAdvertiseById(@RequestHeader("auth-token") String authToken,
			@PathVariable("id") int advertiseId);

	public boolean deleteAdvertiseById(@RequestHeader("auth-token") String authToken,
			@PathVariable("id") int advertiseId);

	public ResponseEntity<AdveriseData> searchAdvertiseByFiltercriteria(String searchText, int categoryId, String postedBy,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortBy, int startIndex,
			int records);

	public ResponseEntity<AdveriseData> searchAdvertiseByText(@RequestParam("searchText") String searchText);

	public Advertisement getAdvertiseDetailById(@RequestHeader("auth-token") String authToken,
			@PathVariable("id") int advertiseId);

	// Find Advertise by Title
	List<Advertisement> findByTitle(String title);

	// Find Advertise by Category
	List<Advertisement> findByCategory(int category);

	// Find Advertise for Between condition
	List<Advertisement> findByCreatedDateBetweenAndUsernameAndTitleContainingAndCategory(LocalDate formDate, LocalDate toDate, String username, String searchText, int category);
	
	// Find Advertise for LessThan condition
	List<Advertisement> findByCreatedDateLessThanAndUsernameAndTitleContainingAndCategory(LocalDate formDate, String username, String searchText, int category);
	
	// Find Advertise for GreaterThan condition
	List<Advertisement> findByCreatedDateGreaterThanAndUsernameAndTitleContainingAndCategory(LocalDate formDate, String username, String searchText, int category);
	
	// Find Advertise for Equals condition
	List<Advertisement> findByCreatedDateEqualsAndUsernameAndTitleContainingAndCategory(LocalDate onDate, String username, String searchText, int category);
	
	// Find Advertise By Search Keyword
	List<Advertisement> findByText(String searchText);

	// Find Advertise LessThan from date
//	List<Advertise> findByEndLessThan(String dateCondition, LocalDate formDate);
//
//	// Find Advertise GreaterThan from date
//	List<Advertise> findByCreatedGreaterThan(String dateCondition, LocalDate formDate);
//
//	// Find Advertise Equals on date
//	List<AdvertiseEntity> findByModifiedDate(String dateCondition, LocalDate onDate);
}
