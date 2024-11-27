package com.shortify.Shortify_Url_Shortner.repository;

import com.shortify.Shortify_Url_Shortner.model.ShortenedURL;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenedUrlRepository extends MongoRepository<ShortenedURL,String> {
    Optional<ShortenedURL> findByShortURL(String shortURL);
    Optional<ShortenedURL> findByOriginalURL(String originalURL);
}
