package com.urlshortener.dao;

import com.urlshortener.entity.UrlMapping;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UrlMappingDao {

    @PersistenceContext(unitName = "urlShortenerPU")
    private EntityManager em;

    public UrlMapping save(UrlMapping urlMapping) {
        em.persist(urlMapping);
        return urlMapping;
    }

    public UrlMapping findByShortCode(String shortCode) {
        try {
            return em.createQuery("SELECT u FROM UrlMapping u WHERE u.shortCode = :shortCode", UrlMapping.class)
                    .setParameter("shortCode", shortCode)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public UrlMapping findByOriginalUrl(String originalUrl) {
        try {
            return em.createQuery("SELECT u FROM UrlMapping u WHERE u.originalUrl = :originalUrl", UrlMapping.class)
                    .setParameter("originalUrl", originalUrl)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<UrlMapping> findAllOrderByCreatedAtDesc() {
        return em.createQuery("SELECT u FROM UrlMapping u ORDER BY u.createdAt DESC", UrlMapping.class)
                .getResultList();
    }

    public UrlMapping update(UrlMapping urlMapping) {
        return em.merge(urlMapping);
    }

    public void delete(Long id) {
        UrlMapping urlMapping = em.find(UrlMapping.class, id);
        if (urlMapping != null) {
            em.remove(urlMapping);
        }
    }
}