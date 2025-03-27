package com.sachinda.urlcreationservice.repository;

import com.sachinda.urlcreationservice.entity.ShortURLEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ShortURLRepository extends MongoRepository<ShortURLEntity, String> {
    List<ShortURLEntity> findAllByUserId(String userId);

    Optional<ShortURLEntity> findByOriginalUrl(String originalURL);

    Optional<ShortURLEntity> findByShortUrlKey(String shortUrlKey);
}
