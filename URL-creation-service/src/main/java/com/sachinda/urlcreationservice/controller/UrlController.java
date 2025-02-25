package com.sachinda.urlcreationservice.controller;

import com.sachinda.urlcreationservice.dto.ShortUrlDto;
import com.sachinda.urlcreationservice.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shorturl")
public class UrlController {

    @Autowired
    private URLService urlService;

    @PostMapping
    public String createURl(@RequestBody ShortUrlDto shortUrlDto){
        return urlService.createURL(shortUrlDto);
    }

    @GetMapping("/{id}")
    public ShortUrlDto createURl(@PathVariable String id){
        return urlService.getShortUrl(id);
    }

    @GetMapping("getAllByUser/{userId}")
    public List<ShortUrlDto> getByUser(@PathVariable String userId){
        return urlService.getAllShortUrls(userId);
    }

    @DeleteMapping("/{id}")
    public String deleteURl(@PathVariable String id){
        return urlService.deleteShortUrl(id);
    }

}
