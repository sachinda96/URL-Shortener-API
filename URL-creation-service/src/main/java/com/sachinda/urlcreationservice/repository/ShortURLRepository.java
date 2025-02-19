package com.sachinda.urlcreationservice.repository;

import com.sachinda.urlcreationservice.Entity.ShortURLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortURLRepository extends MongoRepository<ShortURLEntity, Integer> {
}
