package com.sachinda.urlshortingservice.repository;

import com.sachinda.urlshortingservice.Entity.ShortURLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortURLRepository extends MongoRepository<ShortURLEntity, Integer> {
}
