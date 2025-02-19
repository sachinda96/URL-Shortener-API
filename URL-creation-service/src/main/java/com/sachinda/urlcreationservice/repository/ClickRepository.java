package com.sachinda.urlcreationservice.repository;

import com.sachinda.urlcreationservice.Entity.ClickEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClickRepository extends MongoRepository<ClickEntity, Integer> {
}
