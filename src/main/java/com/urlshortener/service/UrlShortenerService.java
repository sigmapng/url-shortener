package com.urlshortener.service;

import com.urlshortener.dao.UrlMappingDao;
import com.urlshortener.entity.UrlMapping;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.SecureRandom;
import java.util.List;

@Stateless
public class UrlShortenerService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_CODE_LENGTH = 6;
    private final SecureRandom random = new SecureRandom();

    @Inject
    private UrlMappingDao urlMappingDao;

    public UrlMapping createShortUrl(String originalUrl) {
        UrlMapping existingMapping = urlMappingDao.findByOriginalUrl(originalUrl);
        if (existingMapping != null) {
            return existingMapping;
        }

        String shortCode = generateShortCode();
        while (urlMappingDao.findByShortCode(shortCode) != null) {
            shortCode = generateShortCode();
        }

        UrlMapping urlMapping = new UrlMapping(originalUrl, shortCode);
        return urlMappingDao.save(urlMapping);
    }

    public UrlMapping getOriginalUrl(String shortCode) {
        return urlMappingDao.findByShortCode(shortCode);
    }

    public List<UrlMapping> getAllUrls() {
        return urlMappingDao.findAllOrderByCreatedAtDesc();
    }

    private String generateShortCode() {
        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}

