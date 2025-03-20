package com.sachinda.urlcreationservice.service.impl;

import com.sachinda.urlcreationservice.dto.ShortUrlDto;
import com.sachinda.urlcreationservice.dto.ShortUrlResponseDto;
import com.sachinda.urlcreationservice.entity.ShortURLEntity;
import com.sachinda.urlcreationservice.repository.ShortURLRepository;
import com.sachinda.urlcreationservice.service.URLService;
import com.sachinda.urlcreationservice.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class URLServiceImpl implements URLService {

    private final static String BASE_URL = "http://localhost:8080/";

    private static final Random random = new Random();


    @Autowired
    private ShortURLRepository shortURLRepository;

    @Override
    public ShortUrlResponseDto createURL(ShortUrlDto shortUrlDto) {

        Optional<ShortURLEntity> shortURLEntity = shortURLRepository.findByOriginalUrl(shortUrlDto.getOriginalURL());

        if(shortURLEntity.isPresent()){

            return new ShortUrlResponseDto(BASE_URL+shortURLEntity.get().getShortUrlKey(), shortURLEntity.get().getExpiresAt());
        }

        String shortUrlKey = HashUtil.generateMD5(shortUrlDto.getOriginalURL());

        while (shortURLRepository.findByShortUrlKey(shortUrlKey).isPresent()) {
            shortUrlKey = resolveCollision(shortUrlKey);
        }

        ShortURLEntity shortUrl= new ShortURLEntity();
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

        shortURLRepository.save(shortUrl);

        return new ShortUrlResponseDto(BASE_URL + shortUrlKey, shortUrl.getExpiresAt());
    }

    @Override
    public ShortUrlDto getShortUrl(String key) {

        Optional<ShortURLEntity> shortURLEntity = shortURLRepository.findById(key);
        return setShortUrlDto(shortURLEntity.get());
    }

    @Override
    public List<ShortUrlDto> getAllShortUrls(String userId) {
        List<ShortURLEntity> shortURLEntities = shortURLRepository.findAllByUserID(userId);
        List<ShortUrlDto> shortUrlDtoList = new ArrayList<>();

        for (ShortURLEntity shortURLEntity: shortURLEntities){
            shortUrlDtoList.add(setShortUrlDto(shortURLEntity));
        }
        return shortUrlDtoList;
    }

    @Override
    public String deleteShortUrl(String id) {

        shortURLRepository.deleteById(id);

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
