package com.sachinda.urlcreationservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collation = "clicks")
public class ClickEntity {

    @Id
    private Integer id;

    private Integer  shortURLId;

    private String ipAddress;

    private Date ClickedAt;

    private String referrer;

    private String userAgent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShortURLId() {
        return shortURLId;
    }

    public void setShortURLId(Integer shortURLId) {
        this.shortURLId = shortURLId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getClickedAt() {
        return ClickedAt;
    }

    public void setClickedAt(Date clickedAt) {
        ClickedAt = clickedAt;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
