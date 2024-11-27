package com.shortify.Shortify_Url_Shortner.service;

import com.shortify.Shortify_Url_Shortner.exceptions.UrlNotFoundException;
import com.shortify.Shortify_Url_Shortner.model.ShortenedURL;
import com.shortify.Shortify_Url_Shortner.repository.ShortenedUrlRepository;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class UrlShortenerServiceImpl implements UrlShortenerService {
    private final ShortenedUrlRepository repository;
    private String baseURL="https://shortify";
    private int defaultExpirationDays = 30;
    public UrlShortenerServiceImpl(ShortenedUrlRepository repository){
        this.repository=repository;
    }

    @Override
    public ShortenedURL shortenURL(String originalURL) {
        Optional<ShortenedURL> existingURL = repository.findByOriginalURL(originalURL);
        if(existingURL.isPresent()){
           if(existingURL.get().getExpiresAt()!=null &&
                existingURL.get().getExpiresAt().isBefore(LocalDateTime.now()))
           {
               repository.delete(existingURL.get());
           }
           else{
               existingURL.get();
           }
        }
        String uniqueID= UUID.randomUUID().toString().substring(0,5);
        String shortenedURL = baseURL + '/' + uniqueID;
        LocalDateTime expirationTime = LocalDateTime.now().plusDays(defaultExpirationDays);
        ShortenedURL urlEntity = new ShortenedURL();
        urlEntity.setOriginalURL(originalURL);
        urlEntity.setShortURL(shortenedURL);
        urlEntity.setCreatedAt(LocalDateTime.now());
        urlEntity.setExpiresAt(expirationTime);

        return repository.save(urlEntity);
    }

    @Override
    public String getOriginalURL(String shortURL) {
        ShortenedURL urlEntity = repository.findByShortURL(shortURL)
                .orElseThrow(() -> new UrlNotFoundException("URL not found: " + shortURL));
        if(urlEntity.getExpiresAt()!=null && urlEntity.getExpiresAt().isBefore(LocalDateTime.now())){
            throw new UrlNotFoundException("URL Expired");
        }
        return urlEntity.getOriginalURL();
    }
}
