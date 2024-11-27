package com.shortify.Shortify_Url_Shortner.service;

import com.shortify.Shortify_Url_Shortner.model.ShortenedURL;

public interface UrlShortenerService {
    ShortenedURL shortenURL (String originalURL);
    String getOriginalURL (String shortURL);
}
