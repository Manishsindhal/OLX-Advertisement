package com.olx.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olx.entity.CategoryEntity;

public interface MasterdataCategoryRepo extends JpaRepository<CategoryEntity, Integer>{

}