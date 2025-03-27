package com.sachinda.urlshortingservice.controller;

import com.sachinda.urlshortingservice.service.ShortURLService;
import com.sachinda.urlshortingservice.service.impl.ShortURLServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ShortUrlController {

    @Autowired
    private ShortURLService shortURLService;

    Logger logger = LoggerFactory.getLogger(ShortUrlController.class);

    @GetMapping("/{shortKey}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortKey, HttpServletRequest request) {
        logger.info("Received request to fetch the original URL with short key : {}",shortKey);
        String ipAddress = request.getRemoteAddr();
        String originalUrl = shortURLService.getOriginalURL(shortKey,ipAddress);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        //return ResponseEntity.status(302).location(URI.create(originalUrl)).build();
    }
}
