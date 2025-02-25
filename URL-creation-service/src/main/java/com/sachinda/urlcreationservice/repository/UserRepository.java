package com.sachinda.urlcreationservice.repository;

import com.sachinda.urlcreationservice.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, Integer> {
}
