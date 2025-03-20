package com.sachinda.urlshortingservice.service.impl;

import com.sachinda.urlshortingservice.Entity.ClickEntity;
import com.sachinda.urlshortingservice.Entity.ShortURLEntity;
import com.sachinda.urlshortingservice.repository.ClickRepository;
import com.sachinda.urlshortingservice.repository.ShortURLRepository;
import com.sachinda.urlshortingservice.service.ShortURLService;
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

    @Override
    public String getOriginalURL(String shortUrlKey, String ipAddress) {

       ShortURLEntity shortURLEntity = shortURLRepository.findByShortUrlKey(shortUrlKey);

        ClickEntity clickEntity = new ClickEntity();
        clickEntity.setId(UUID.randomUUID().toString());
        clickEntity.setCreatedDate(new Date());
        clickEntity.setIpAddress(ipAddress);
        clickEntity.setClickedAt(new Date());
        clickEntity.setShorturlId(shortURLEntity.getId());

        clickRepository.save(clickEntity);

       return shortURLEntity.getOriginalUrl();
    }
}
