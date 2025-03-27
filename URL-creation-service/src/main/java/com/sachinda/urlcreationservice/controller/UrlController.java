package com.sachinda.urlcreationservice.controller;

import com.sachinda.urlcreationservice.dto.ShortUrlDto;
import com.sachinda.urlcreationservice.dto.ShortUrlResponseDto;
import com.sachinda.urlcreationservice.service.URLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shorturl")
public class UrlController {

    private static final Logger logger = LoggerFactory.getLogger(UrlController.class);

    @Autowired
    private URLService urlService;


    @PostMapping
    public ShortUrlResponseDto createURl(@RequestBody ShortUrlDto shortUrlDto){
        logger.info("Received request to create short URL for: {}", shortUrlDto.getOriginalURL());
        return urlService.createURL(shortUrlDto);
    }

    @GetMapping("/{id}")
    public ShortUrlDto createURl(@PathVariable String id){
        logger.info("Received request to fetch short URL for ID: {}", id);
        return urlService.getShortUrl(id);
    }

    @GetMapping("getAllByUser/{userId}")
    public List<ShortUrlDto> getByUser(@PathVariable String userId){
        logger.info("Received request to fetch all short URLs for user ID: {}", userId);
        return urlService.getAllShortUrls(userId);
    }

    @DeleteMapping("/{id}")
    public String deleteURl(@PathVariable String id){
        logger.info("Received request to delete short URL with ID: {}", id);
        return urlService.deleteShortUrl(id);
    }

}
