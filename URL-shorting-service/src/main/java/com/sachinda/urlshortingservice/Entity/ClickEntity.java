package com.sachinda.urlshortingservice.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collation = "clicks")
public class ClickEntity {

    @Id
    private String id;

    private String  shorturlId;

    private String ipAddress;

    private Date clickedAt;

    private Date createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShorturlId() {
        return shorturlId;
    }

    public void setShorturlId(String shorturlId) {
        this.shorturlId = shorturlId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getClickedAt() {
        return clickedAt;
    }

    public void setClickedAt(Date clickedAt) {
        this.clickedAt = clickedAt;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
