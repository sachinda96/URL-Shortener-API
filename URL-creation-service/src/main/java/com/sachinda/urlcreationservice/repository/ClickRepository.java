package com.sachinda.urlcreationservice.repository;

import com.sachinda.urlcreationservice.entity.ClickEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClickRepository extends MongoRepository<ClickEntity, String> {
}
