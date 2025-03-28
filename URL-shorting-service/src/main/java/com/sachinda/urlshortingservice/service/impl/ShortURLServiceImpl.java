package com.sachinda.urlshortingservice.service.impl;

import com.sachinda.urlshortingservice.Entity.ClickEntity;
import com.sachinda.urlshortingservice.Entity.ShortURLEntity;
import com.sachinda.urlshortingservice.exception.custom.DatabaseException;
import com.sachinda.urlshortingservice.exception.custom.ResourceNotFoundException;
import com.sachinda.urlshortingservice.repository.ClickRepository;
import com.sachinda.urlshortingservice.repository.ShortURLRepository;
import com.sachinda.urlshortingservice.service.ShortURLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ShortURLServiceImpl implements ShortURLService {

    @Autowired
    private ShortURLRepository shortURLRepository;

    @Autowired
    private ClickRepository clickRepository;

    Logger logger = LoggerFactory.getLogger(ShortURLServiceImpl.class);


    @Override
    public String getOriginalURL(String shortUrlKey, String ipAddress) {

            if(shortUrlKey == null){
                logger.error("Short key cannot be null");
                throw new ResourceNotFoundException("Short url key is invalid");
            }

            logger.info("Fetching the original url with short key: {}",shortUrlKey);
            ShortURLEntity shortURLEntity = shortURLRepository.findByShortUrlKey(shortUrlKey);

            if(shortURLEntity == null){
                logger.error("Fetching failed, the short key is invalid");
                throw new ResourceNotFoundException("Short url not found for key "+ shortUrlKey);
            }

            try {
                ClickEntity clickEntity = new ClickEntity();
                clickEntity.setId(UUID.randomUUID().toString());
                clickEntity.setCreatedDate(new Date());
                clickEntity.setIpAddress(ipAddress);
                clickEntity.setClickedAt(new Date());
                clickEntity.setShorturlId(shortURLEntity.getId());

                clickRepository.save(clickEntity);
            }catch (Exception e){
                throw new DatabaseException("Failed to save the click data to the database", e);
            }

            logger.debug("Successfully retrieved original url with Short key: {}", shortUrlKey);
            return shortURLEntity.getOriginalUrl();

    }
}
