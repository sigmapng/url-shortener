package com.urlshortener.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "urls", schema = "public")
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "original_url", length = 2000, nullable = false)
    private String originalUrl;

    @Column(name = "short_code", length = 10, nullable = false, unique = true)
    private String shortCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UrlMapping() {
    }

    public UrlMapping(String originalUrl, String shortCode) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.createdAt = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }

    public String getShortCode() { return shortCode; }
    public void setShortCode(String shortCode) { this.shortCode = shortCode; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
