package com.sachinda.urlshortingservice.repository;

import com.sachinda.urlshortingservice.Entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, Integer> {
}
