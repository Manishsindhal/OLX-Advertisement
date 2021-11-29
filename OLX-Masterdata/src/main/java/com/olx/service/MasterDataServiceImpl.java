package com.olx.service;

import java.util.ArrayList;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.olx.dto.Category;
import com.olx.dto.Status;

import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;
import com.olx.repo.MasterdataCategoryRepo;
import com.olx.repo.MasterdataStatusRepo;
import com.olx.utility.MasterdataCategory;
import com.olx.utility.MasterdataStatus;

@Service(value = "JPA_SERVICE")
public class MasterDataServiceImpl implements MasterDataService {

	@Autowired
	AdvertiseDelegate advertiseDelegate;

	@Autowired
	MasterdataCategoryRepo masterdataCategoryRepo;

	@Autowired
	MasterdataStatusRepo masterdataStatusRepo;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	MasterdataCategory masterdataCategory;
	
	@Autowired
	MasterdataStatus masterdataStatus;

	@Override
	public ResponseEntity<MasterdataCategory> getAllCategories() {
		// Collection<Map> adList = advertiseDelegate.getAllAdvertisement();

		List<CategoryEntity> categoryEntities = masterdataCategoryRepo.findAll();
		return getCategoryDtoList(categoryEntities);
	}

	private ResponseEntity<MasterdataCategory> getCategoryDtoList(List<CategoryEntity> categoryEntitiesList) {
		List<Category> categoryDtoList = new ArrayList<Category>();
		for (CategoryEntity categoryEntity : categoryEntitiesList) {
			Category categoryDto = this.modelMapper.map(categoryEntity, Category.class);
			categoryDtoList.add(categoryDto);
			masterdataCategory.setCategory(categoryDtoList);
		}
		return new ResponseEntity<MasterdataCategory>(masterdataCategory, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<MasterdataStatus> getAllStatus() {
		List<StatusEntity> statusEntities = masterdataStatusRepo.findAll();
		return getStatusDtoList(statusEntities);
	}
	
	private ResponseEntity<MasterdataStatus> getStatusDtoList(List<StatusEntity> statusEntitiesList) {
		List<Status> statusDtoList = new ArrayList<Status>();
		for (StatusEntity statusEntity : statusEntitiesList) {
			Status statusDto = this.modelMapper.map(statusEntity, Status.class);
			statusDtoList.add(statusDto);
			masterdataStatus.setStatus(statusDtoList);
		}
		return new ResponseEntity<MasterdataStatus>(masterdataStatus, HttpStatus.OK);
	}

}
