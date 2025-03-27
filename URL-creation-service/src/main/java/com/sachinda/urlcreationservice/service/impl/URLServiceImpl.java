package com.sachinda.urlcreationservice.service.impl;

import com.sachinda.urlcreationservice.dto.ShortUrlDto;
import com.sachinda.urlcreationservice.dto.ShortUrlResponseDto;
import com.sachinda.urlcreationservice.entity.ShortURLEntity;
import com.sachinda.urlcreationservice.exception.custom.DatabaseException;
import com.sachinda.urlcreationservice.exception.custom.ResourceNotFoundException;
import com.sachinda.urlcreationservice.exception.custom.UrlNotFoundException;
import com.sachinda.urlcreationservice.repository.ShortURLRepository;
import com.sachinda.urlcreationservice.service.URLService;
import com.sachinda.urlcreationservice.util.HashUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class URLServiceImpl implements URLService {

    private final static String BASE_URL = "http://localhost:8082/";

    private static final Random random = new Random();

    private static final Logger logger =  LoggerFactory.getLogger(URLServiceImpl.class);

    @Autowired
    private ShortURLRepository shortURLRepository;

    @Override
    public ShortUrlResponseDto createURL(ShortUrlDto shortUrlDto) {

        logger.info("Received request to create short URL for: {}", shortUrlDto.getOriginalURL());

        Optional<ShortURLEntity> shortURLEntity = shortURLRepository.findByOriginalUrl(shortUrlDto.getOriginalURL());

        if(shortURLEntity.isPresent()){
            logger.info("URL already exists, returning existing short URL: {}", BASE_URL + shortURLEntity.get().getShortUrlKey());
            return new ShortUrlResponseDto(BASE_URL+shortURLEntity.get().getShortUrlKey(), shortURLEntity.get().getExpiresAt());
        }

        String shortUrlKey = HashUtil.generateMD5(shortUrlDto.getOriginalURL());
        logger.debug("Generated initial short URL key: {}", shortUrlKey);

        while (shortURLRepository.findByShortUrlKey(shortUrlKey).isPresent()) {
            logger.warn("Collision detected for key: {}, generating a new key.", shortUrlKey);
            shortUrlKey = resolveCollision(shortUrlKey);
        }

        try {

            ShortURLEntity shortUrl = new ShortURLEntity();
            shortUrl.setId(UUID.randomUUID().toString());
            shortUrl.setOriginalUrl(shortUrlDto.getOriginalURL());
            shortUrl.setShortUrlKey(shortUrlKey);

            Date currentDate = new Date();
            shortUrl.setCreatedAt(currentDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.MONTH, 1);

            shortUrl.setExpiresAt(calendar.getTime());

            shortUrl.setUserId(shortUrlDto.getUserID());

            logger.info("Saving new short URL: {} -> {}", shortUrlDto.getOriginalURL(), BASE_URL + shortUrlKey);
            shortURLRepository.save(shortUrl);

            logger.info("Short URL successfully created: {}", BASE_URL + shortUrlKey);
            return new ShortUrlResponseDto(BASE_URL + shortUrlKey, shortUrl.getExpiresAt());
        } catch (Exception e){
            logger.error("Error saving short URL to the database: {}", e.getMessage(), e);
            throw new DatabaseException("Failed to save the short URL to the database", e);
        }
    }

    @Override
    public ShortUrlDto getShortUrl(String key) {
        logger.info("Fetching the short URL data for key: {}", key);
        Optional<ShortURLEntity> shortURLEntity = shortURLRepository.findById(key);

        if(shortURLEntity.isEmpty()){
            logger.warn("No short URL found for key: {}", key);
            throw new ResourceNotFoundException("No short URL found for key: " + key);
        }

        logger.info("Short URL data found for key: {}", key);
        return setShortUrlDto(shortURLEntity.get());
    }

    @Override
    public List<ShortUrlDto> getAllShortUrls(String userId) {

        logger.info("Fetching all short URLs for user ID: {}", userId);

        List<ShortURLEntity> shortURLEntities = shortURLRepository.findAllByUserId(userId);

        if (shortURLEntities.isEmpty()) {
            logger.warn("No short URLs found for user ID: {}", userId);
        } else {
            logger.info("Found {} short URLs for user ID: {}", shortURLEntities.size(), userId);
        }

        List<ShortUrlDto> shortUrlDtoList = new ArrayList<>();
        for (ShortURLEntity shortURLEntity: shortURLEntities){
            shortUrlDtoList.add(setShortUrlDto(shortURLEntity));
        }
        return shortUrlDtoList;
    }

    @Override
    public String deleteShortUrl(String id) {

        logger.info("Deleting short URL data with ID: {}", id);

        if(shortURLRepository.existsById(id)){
            logger.warn("Short URL with ID {} not found, cannot delete.", id);
            throw new ResourceNotFoundException("No short URL data found for id: " + id);
        }

        shortURLRepository.deleteById(id);
        logger.info("Successfully deleted short URL with ID: {}", id);

        return "200";
    }

    private ShortUrlDto setShortUrlDto(ShortURLEntity shortURLEntity){
        ShortUrlDto shortUrlDto = new ShortUrlDto();
        shortUrlDto.setOriginalURL(shortURLEntity.getOriginalUrl());
        shortUrlDto.setShortURL(shortURLEntity.getShortUrlKey());
        shortUrlDto.setUserID(shortURLEntity.getUserId());
        shortUrlDto.setId(shortURLEntity.getId());
        shortUrlDto.setCreatedAt(shortURLEntity.getCreatedAt());

        return shortUrlDto;
    }


    private String resolveCollision(String shortUrl) {
        return shortUrl + random.nextInt(10); // Append a digit to reduce collision
    }
}
