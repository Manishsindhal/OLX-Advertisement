package com.olx.service;

import java.util.ArrayList;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.olx.dto.Category;
import com.olx.dto.Status;

import com.olx.entity.CategoryEntity;
import com.olx.entity.StatusEntity;
import com.olx.repo.MasterdataCategoryRepo;
import com.olx.repo.MasterdataStatusRepo;

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

	@Override
	public List<Category> getAllCategories() {
		// Collection<Map> adList = advertiseDelegate.getAllAdvertisement();

		List<CategoryEntity> categoryEntities = masterdataCategoryRepo.findAll();
		return getCategoryDtoList(categoryEntities);
	}

	private List<Category> getCategoryDtoList(List<CategoryEntity> categoryEntitiesList) {
		List<Category> categoryDtoList = new ArrayList<Category>();
		for (CategoryEntity categoryEntity : categoryEntitiesList) {
			Category categoryDto = this.modelMapper.map(categoryEntity, Category.class);
			categoryDtoList.add(categoryDto);
		}
		return categoryDtoList;
	}
	
	@Override
	public List<Status> getAllStatus() {
		List<StatusEntity> statusEntities = masterdataStatusRepo.findAll();
		return getStatusDtoList(statusEntities);
	}
	
	private List<Status> getStatusDtoList(List<StatusEntity> statusEntitiesList) {
		List<Status> statusDtoList = new ArrayList<Status>();
		for (StatusEntity statusEntity : statusEntitiesList) {
			Status statusDto = this.modelMapper.map(statusEntity, Status.class);
			statusDtoList.add(statusDto);
		}
		return statusDtoList;
	}

}
