package com.olx.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.olx.dto.Advertisement;
import com.olx.entity.AdvertisementEntity;


public interface AdvertiseRepo extends JpaRepository<AdvertisementEntity, Integer> {

	// Find Advertise by Title
	List<AdvertisementEntity> findByTitle(String title);

	// Find Advertise by Category
	List<AdvertisementEntity> findByCategory(int category);

	// Find Advertise form date Between
	List<AdvertisementEntity> findByCreatedDateBetweenAndUsernameAndTitleContainingAndCategory(LocalDate formDate, LocalDate toDate, String username, String title, int category);
	
	// Find Advertise for LessThan condition
	List<AdvertisementEntity> findByCreatedDateLessThanAndUsernameAndTitleContainingAndCategory(LocalDate formDate, String username, String searchText, int category);
	
	// Find Advertise for GreaterThan condition
	List<AdvertisementEntity> findByCreatedDateGreaterThanAndUsernameAndTitleContainingAndCategory(LocalDate formDate, String username, String searchText, int category);
	
	// Find Advertise for Equals condition
	List<AdvertisementEntity> findByCreatedDateEqualsAndUsernameAndTitleContainingAndCategory(LocalDate onDate, String username, String searchText, int category);
	
	// Find Advertise By Search Keyword
	@Query("SELECT a FROM AdvertisementEntity a WHERE a.title LIKE %:searchText% or a.category LIKE %:searchText% or a.description LIKE %:searchText% or a.price LIKE %:searchText% or a.postedBy LIKE %:searchText%"+
			" or a.createdDate LIKE %:searchText% or a.modifiedDate LIKE %:searchText%")
	List<AdvertisementEntity> findByText(String searchText);

	// Find Advertise LessThan from date
//	List<AdvertiseEntity> findByEndLessThan(String dateCondition, LocalDate formDate);
//
//	// Find Advertise GreaterThan from date
//	List<AdvertiseEntity> findByCreatedGreaterThan(String dateCondition, LocalDate formDate);
//
//	// Find Advertise Equals on date
//	List<AdvertiseEntity> findByModifiedDate(String dateCondition, LocalDate onDate);

}
