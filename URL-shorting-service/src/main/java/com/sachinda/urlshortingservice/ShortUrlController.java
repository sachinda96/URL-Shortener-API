package com.sachinda.urlshortingservice;

import com.sachinda.urlshortingservice.service.ShortURLService;
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
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortKey) {
        String originalUrl = shortURLService.getOriginalURL(shortKey);
        return ResponseEntity.status(302).location(URI.create(originalUrl)).build();
    }
}
