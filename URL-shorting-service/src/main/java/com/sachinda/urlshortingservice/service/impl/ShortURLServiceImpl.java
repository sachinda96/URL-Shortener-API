package com.sachinda.urlshortingservice.service.impl;

import com.sachinda.urlshortingservice.Entity.ShortURLEntity;
import com.sachinda.urlshortingservice.repository.ShortURLRepository;
import com.sachinda.urlshortingservice.service.ShortURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortURLServiceImpl implements ShortURLService {

    @Autowired
    private ShortURLRepository shortURLRepository;

    @Override
    public String getOriginalURL(String shortUrlKey) {
       ShortURLEntity shortURLEntity = shortURLRepository.findByShortUrlKey(shortUrlKey);
        return shortURLEntity.getOriginalUrl();
    }
}
