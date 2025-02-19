package com.sachinda.urlshortingservice.repository;


import com.sachinda.urlshortingservice.Entity.ClickEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClickRepository extends MongoRepository<ClickEntity, Integer> {
}
