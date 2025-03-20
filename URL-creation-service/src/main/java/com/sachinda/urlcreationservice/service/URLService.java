package com.sachinda.urlcreationservice.service;

import com.sachinda.urlcreationservice.dto.ShortUrlDto;
import com.sachinda.urlcreationservice.dto.ShortUrlResponseDto;

import java.util.List;

public interface URLService {

    public ShortUrlResponseDto createURL(ShortUrlDto shortUrlDto);

    public ShortUrlDto getShortUrl(String id);

    public List<ShortUrlDto> getAllShortUrls(String userId);

    public String deleteShortUrl(String id);


}
