package com.sachinda.urlcreationservice.service.impl;

import com.sachinda.urlcreationservice.dto.ShortUrlDto;
import com.sachinda.urlcreationservice.entity.ShortURLEntity;
import com.sachinda.urlcreationservice.repository.ShortURLRepository;
import com.sachinda.urlcreationservice.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;

@Service
public class URLServiceImpl implements URLService {

    private final static String BASE_URL = "http://localhost:8080/";


    @Autowired
    private ShortURLRepository shortURLRepository;

    @Override
    public String createURL(ShortUrlDto shortUrlDto) {

        String shortUrlKey = generateShortCode(shortUrlDto.getOriginalURL());
        ShortURLEntity shorturl= new ShortURLEntity();
        shorturl.setId(UUID.randomUUID().toString());
        shorturl.setOriginalUrl(shortUrlDto.getOriginalURL());
        shorturl.setShortUrlKey(shortUrlKey);

        Date currentDate = new Date();
        shorturl.setCreatedAt(currentDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, 1);

        shorturl.setExpiresAt(calendar.getTime());

        shorturl.setUserId(shortUrlDto.getUserID());

        shortURLRepository.save(shorturl);

        return BASE_URL + shortUrlKey;
    }

    @Override
    public ShortUrlDto getShortUrl(String id) {

        Optional<ShortURLEntity> shortURLEntity = shortURLRepository.findById(id);
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

    private String generateShortCode(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash).substring(0, 6);
        } catch (Exception e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}
