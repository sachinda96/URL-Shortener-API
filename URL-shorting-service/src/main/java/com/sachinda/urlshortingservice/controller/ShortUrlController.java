package com.sachinda.urlshortingservice.controller;

import com.sachinda.urlshortingservice.service.ShortURLService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ShortUrlController {

    @Autowired
    private ShortURLService shortURLService;

    @GetMapping("/{shortKey}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortKey, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        String originalUrl = shortURLService.getOriginalURL(shortKey,ipAddress);
        return ResponseEntity.status(302).location(URI.create(originalUrl)).build();
    }
}
