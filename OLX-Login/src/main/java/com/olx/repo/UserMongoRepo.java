package com.olx.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.dto.JwtBlacklist;
import com.olx.entity.UserBlackListTokenDocument;


public interface UserMongoRepo extends MongoRepository<UserBlackListTokenDocument, Integer> {


}
