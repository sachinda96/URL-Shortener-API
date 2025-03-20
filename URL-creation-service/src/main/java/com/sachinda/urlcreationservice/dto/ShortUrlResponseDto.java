package com.sachinda.urlcreationservice.dto;

import java.util.Date;

public class ShortUrlResponseDto {

    private String shortUrl;
    private Date expiredDate;

    public ShortUrlResponseDto(String shortUrl, Date expiredDate) {
        this.shortUrl = shortUrl;
        this.expiredDate = expiredDate;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
}
