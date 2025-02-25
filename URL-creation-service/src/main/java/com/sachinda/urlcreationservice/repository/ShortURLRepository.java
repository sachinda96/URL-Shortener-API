package com.sachinda.urlcreationservice.repository;

import com.sachinda.urlcreationservice.entity.ShortURLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShortURLRepository extends MongoRepository<ShortURLEntity, String> {
    List<ShortURLEntity> findAllByUserID(String userId);
}
