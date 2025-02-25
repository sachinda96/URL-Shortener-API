package com.sachinda.urlcreationservice.dto;

import java.util.Date;

public class ShortUrlDto {

    private String id;
    private String originalURL;

    private String shortURL;

    private Date createdAt;

    private Date ExpiresAt;

        private Integer userID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiresAt() {
        return ExpiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        ExpiresAt = expiresAt;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
