package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertisement;
import com.olx.entity.AdvertisementEntity;
import com.olx.exception.InvalidAdvertiseIdExeption;
import com.olx.exception.InvalidAuthTokenExeption;
import com.olx.exception.InvalidCategoryIdExeption;
import com.olx.exception.InvalidRecordNoExeption;
import com.olx.exception.InvalidStatusIdExeption;
import com.olx.repo.AdvertiseRepo;

@Service(value = "JPA_SERVICE")
public class AdvertiseServiceImpl implements AdvertiseService {

	@Autowired
	AdvertiseRepo advertiseRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	MasterDataDelegate masterDataDelegate;

	@Autowired
	LoginDelegate loginDelegate;

	private boolean isValidAuthToken(String authToken) {
		ResponseEntity<Boolean> isValidToken = loginDelegate.validateToken(authToken);
		boolean boolValue = isValidToken.getBody().booleanValue();
		return boolValue;
	}

	@Override
	public Advertisement CreateNewAdvertise(String authToken, Advertisement advertise) {
		if (isValidAuthToken(authToken)) {
			if (isCategoryExist(advertise.getCategory())) {
				if (isStatusExist(advertise.getStatus())) {
					AdvertisementEntity advertiseEntity = this.modelMapper.map(advertise, AdvertisementEntity.class);
					advertiseEntity = this.advertiseRepo.save(advertiseEntity);
					Advertisement advertiseDTO = this.modelMapper.map(advertiseEntity, Advertisement.class);
					return advertiseDTO;
				}
				throw new InvalidStatusIdExeption("" + advertise.getStatus());
			}
			throw new InvalidCategoryIdExeption("" + advertise.getCategory());
		} else {
			throw new InvalidAuthTokenExeption();
		}
	}

	@Override
	public Advertisement updateAdvertiseById(String authToken, int advertiseId, Advertisement newAdvertise) {
		if (isValidAuthToken(authToken)) {
			Optional<AdvertisementEntity> optAdvertiseEntity = advertiseRepo.findById(advertiseId);
			if (optAdvertiseEntity.isPresent()) {
				if (isCategoryExist(newAdvertise.getCategory())) {
					if (isStatusExist(newAdvertise.getStatus())) {
						AdvertisementEntity advertiseEntity = optAdvertiseEntity.get();
						newAdvertise.setId(advertiseId);
						advertiseEntity.setTitle(newAdvertise.getTitle());
						advertiseEntity.setCategory(newAdvertise.getCategory());
						advertiseEntity.setStatus(newAdvertise.getStatus());
						advertiseEntity.setPrice(newAdvertise.getPrice());
						advertiseEntity.setDescription(newAdvertise.getDescription());
						advertiseEntity.setCreatedDate(newAdvertise.getCreatedDate());
						advertiseEntity.setModifiedDate(newAdvertise.getModifiedDate());
						advertiseEntity.setActive(newAdvertise.getActive());
						advertiseEntity.setPostedBy(newAdvertise.getPostedBy());
						advertiseEntity.setUsername(newAdvertise.getUsername());

						advertiseEntity = this.modelMapper.map(newAdvertise, AdvertisementEntity.class);
						advertiseEntity = this.advertiseRepo.save(advertiseEntity);
						Advertisement advertiseDTO = this.modelMapper.map(advertiseEntity, Advertisement.class);
						return advertiseDTO;
					}
					throw new InvalidStatusIdExeption("" + newAdvertise.getStatus());
				}
				throw new InvalidCategoryIdExeption("" + newAdvertise.getCategory());
			}
			// return null;
			throw new InvalidAdvertiseIdExeption("" + advertiseId);
		} else {
			throw new InvalidAuthTokenExeption();
		}

	}

	@Override
	public Collection<Advertisement> getAllAdvertisement(String authToken) {
		// return advertiseMap.values();
		if (isValidAuthToken(authToken)) {
			List<AdvertisementEntity> advertiseEntities = advertiseRepo.findAll();
			return getAdvertiseDtoList(advertiseEntities);
		} else {
			throw new InvalidAuthTokenExeption();
		}
	}

	private List<Advertisement> getAdvertiseDtoList(List<AdvertisementEntity> advertiseEntitiesList) {
		List<Advertisement> advertiseDtoList = new ArrayList<Advertisement>();
		for (AdvertisementEntity advertiseEntity : advertiseEntitiesList) {
			Advertisement advertiseDto = this.modelMapper.map(advertiseEntity, Advertisement.class);
			advertiseDtoList.add(advertiseDto);
		}
		if (advertiseDtoList.size() == 0)
			throw new InvalidRecordNoExeption();
		else
			return advertiseDtoList;
	}

	@Override
	public Advertisement getAdvertiseById(String authToken, int advertiseId) {
		// return advertiseMap.get(advertiseId);
		if (isValidAuthToken(authToken)) {
			Optional<AdvertisementEntity> optAdvertiseEntity = advertiseRepo.findById(advertiseId);
			if (optAdvertiseEntity.isPresent()) {
				AdvertisementEntity advertiseEntity = optAdvertiseEntity.get();
				return getAdvertiseDTOFromEntity(advertiseEntity);
			}
			// return null;
			throw new InvalidAdvertiseIdExeption("" + advertiseId);
		} else {
			throw new InvalidAuthTokenExeption();
		}
	}

	private Advertisement getAdvertiseDTOFromEntity(AdvertisementEntity advertiseEntity) {
		TypeMap<Advertisement, AdvertisementEntity> typeMap = this.modelMapper.typeMap(Advertisement.class,
				AdvertisementEntity.class);
		typeMap.addMappings(mapper -> {
			mapper.map(source -> source.getCategory(), AdvertisementEntity::setCategory);
		});

		Advertisement advertiseDTO = this.modelMapper.map(advertiseEntity, Advertisement.class);
		return advertiseDTO;
	}

	@Override
	public boolean deleteAdvertiseById(String authToken, int advertiseId) {
		// advertiseMap.remove(advertiseId);
		if (isValidAuthToken(authToken)) {
			Optional<AdvertisementEntity> optAdvertiseEntity = advertiseRepo.findById(advertiseId);
			if (optAdvertiseEntity.isPresent()) {
				advertiseRepo.deleteById(advertiseId);
				return true;
			}
			throw new InvalidAdvertiseIdExeption("" + advertiseId);
		} else {
			throw new InvalidAuthTokenExeption();
		}
	}

	@Override
	public List<Advertisement> searchAdvertiseByFiltercriteria(String searchText, int categoryId, String username,
			String dateCondition, LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortBy, int startIndex,
			int records) {
		List<AdvertisementEntity> advertiseEntitiesList = advertiseRepo.findAll();
		return getAdvertiseDtoListForFilterCriteria(advertiseEntitiesList, searchText, categoryId, username,
				dateCondition, onDate, fromDate, toDate, sortBy, startIndex, records);

	}

	private boolean isCategoryExist(int categoryId) {
		List<Map> categoriesList = masterDataDelegate.getAllCategories();

		for (Map str : categoriesList) {
			if (str.containsValue("" + categoryId)) {
				System.out.println("yes printed-->");
				return true;
			}
		}
		// return false;
		throw new InvalidCategoryIdExeption("" + categoryId);
	}

	private boolean isStatusExist(int statusId) {
		List<Map> statusList = masterDataDelegate.getAllStatus();

		for (Map str : statusList) {
			if (str.containsValue("" + statusId)) {
				System.out.println("yes printed-->");
				return true;
			}
		}
		// return false;
		throw new InvalidStatusIdExeption("" + statusId);
	}

	private List<Advertisement> getAdvertiseDtoListForFilterCriteria(List<AdvertisementEntity> advertiseEntitiesList,
			String searchText, int categoryId, String username, String dateCondition, LocalDate onDate,
			LocalDate fromDate, LocalDate toDate, String sortBy, int startIndex, int records) {
		// TODO Auto-generated method stub
		List<Advertisement> advertiseDtoList = new ArrayList<Advertisement>();

		if (isCategoryExist(categoryId)) {
			// IgnoreCase
			if (dateCondition.equalsIgnoreCase("Between")) {
				List<AdvertisementEntity> advertiseEntitieList = advertiseRepo
						.findByCreatedDateBetweenAndUsernameAndTitleContainingAndCategory(fromDate, toDate, username,
								searchText, categoryId);
				advertiseDtoList = getAdvertiseDtoList(advertiseEntitieList);

			} else if (dateCondition.equalsIgnoreCase("lessthan")) {
				List<AdvertisementEntity> advertiseEntitieList = advertiseRepo
						.findByCreatedDateLessThanAndUsernameAndTitleContainingAndCategory(fromDate, username,
								searchText, categoryId);
				advertiseDtoList = getAdvertiseDtoList(advertiseEntitieList);

			} else if (dateCondition.equalsIgnoreCase("greatethan")) {
				List<AdvertisementEntity> advertiseEntitieList = advertiseRepo
						.findByCreatedDateGreaterThanAndUsernameAndTitleContainingAndCategory(fromDate, username,
								searchText, categoryId);
				advertiseDtoList = getAdvertiseDtoList(advertiseEntitieList);

			} else if (dateCondition.equalsIgnoreCase("equals")) {
				List<AdvertisementEntity> advertiseEntitieList = advertiseRepo
						.findByCreatedDateEqualsAndUsernameAndTitleContainingAndCategory(onDate, username, searchText,
								categoryId);
				advertiseDtoList = getAdvertiseDtoList(advertiseEntitieList);
			}

		}
		if (advertiseDtoList.size() == 0)
			throw new InvalidRecordNoExeption("");
		else
			return advertiseDtoList;
	}

	@Override
	public List<Advertisement> searchAdvertiseByText(String searchText) {
		// TODO Auto-generated method stub
		List<AdvertisementEntity> advertiseEntities = advertiseRepo.findAll();
		return getAdvertiseDtoListForSearch(advertiseEntities, searchText);
	}

	private List<Advertisement> getAdvertiseDtoListForSearch(List<AdvertisementEntity> advertiseEntitiesList,
			String searchText) {
		List<Advertisement> advertiseDtoList = new ArrayList<Advertisement>();

		List<AdvertisementEntity> advertiseEntitieList = advertiseRepo.findByText(searchText);
		advertiseDtoList = getAdvertiseDtoList(advertiseEntitieList);

		if (advertiseDtoList.size() == 0)
			throw new InvalidRecordNoExeption();
		else
			return advertiseDtoList;

	}

	@Override
	public Advertisement getAdvertiseDetailById(String authToken, int advertiseId) {
		if (isValidAuthToken(authToken)) {
			Optional<AdvertisementEntity> optAdvertiseEntity = advertiseRepo.findById(advertiseId);
			if (optAdvertiseEntity.isPresent()) {
				AdvertisementEntity advertiseEntity = optAdvertiseEntity.get();
				Advertisement advertiseDTO = this.modelMapper.map(advertiseEntity, Advertisement.class);
				return advertiseDTO;
			}
			throw new InvalidAdvertiseIdExeption("" + advertiseId);
		} else {
			throw new InvalidAuthTokenExeption();
		}
	}

	@Override
	public List<Advertisement> findByTitle(String title) {
		List<AdvertisementEntity> advertiseEntitieList = advertiseRepo.findByTitle(title);
		return getAdvertiseDtoList(advertiseEntitieList);
	}

	@Override
	public List<Advertisement> findByCategory(int category) {
		List<AdvertisementEntity> advertiseEntitieList = advertiseRepo.findByCategory(category);
		return getAdvertiseDtoList(advertiseEntitieList);
	}

	@Override
	public List<Advertisement> findByCreatedDateBetweenAndUsernameAndTitleContainingAndCategory(LocalDate formDate,
			LocalDate toDate, String username, String serachText, int category) {
		List<AdvertisementEntity> advertiseEntitieList = advertiseRepo
				.findByCreatedDateBetweenAndUsernameAndTitleContainingAndCategory(formDate, toDate, username,
						serachText, category);
		return getAdvertiseDtoList(advertiseEntitieList);
	}

	@Override
	public List<Advertisement> findByCreatedDateLessThanAndUsernameAndTitleContainingAndCategory(LocalDate formDate,
			String username, String serachText, int category) {
		List<AdvertisementEntity> advertiseEntitieList = advertiseRepo
				.findByCreatedDateLessThanAndUsernameAndTitleContainingAndCategory(formDate, username, serachText,
						category);
		return getAdvertiseDtoList(advertiseEntitieList);
	}

	@Override
	public List<Advertisement> findByCreatedDateGreaterThanAndUsernameAndTitleContainingAndCategory(LocalDate formDate,
			String username, String serachText, int category) {
		List<AdvertisementEntity> advertiseEntitieList = advertiseRepo
				.findByCreatedDateGreaterThanAndUsernameAndTitleContainingAndCategory(formDate, username, serachText,
						category);
		return getAdvertiseDtoList(advertiseEntitieList);
	}

	@Override
	public List<Advertisement> findByCreatedDateEqualsAndUsernameAndTitleContainingAndCategory(LocalDate onDate,
			String username, String serachText, int category) {
		List<AdvertisementEntity> advertiseEntitieList = advertiseRepo
				.findByCreatedDateEqualsAndUsernameAndTitleContainingAndCategory(onDate, username, serachText,
						category);
		return getAdvertiseDtoList(advertiseEntitieList);
	}

	@Override
	public List<Advertisement> findByText(String searchText) {
		List<AdvertisementEntity> advertisementEntity = advertiseRepo.findByText(searchText);
		return getAdvertiseDtoList(advertisementEntity);
	}

//	@Override
//	public List<Advertise> findByEndLessThan(String dateCondition, LocalDate formDate) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Advertise> findByCreatedGreaterThan(String dateCondition, LocalDate formDate) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<AdvertiseEntity> findByModifiedDate(String dateCondition, LocalDate onDate) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
