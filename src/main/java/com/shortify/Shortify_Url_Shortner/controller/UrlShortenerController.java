package com.shortify.Shortify_Url_Shortner.controller;

import com.shortify.Shortify_Url_Shortner.model.ShortenedURL;
import com.shortify.Shortify_Url_Shortner.repository.ShortenedUrlRepository;
import com.shortify.Shortify_Url_Shortner.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlShortenerController {
    @Autowired
    private UrlShortenerService urlShortenerService;
    @Autowired
    private ShortenedUrlRepository shortenedUrlRepository;
    @PostMapping("/shorten")
    public ResponseEntity<String> shorten (@RequestBody String originalURL){
        if(originalURL==null|| originalURL.isEmpty()){
            return  ResponseEntity.badRequest().body("Original URL cannot be empty");
        }
//        System.out.println(originalURL);
        ShortenedURL shortURL = urlShortenerService.shortenURL(originalURL);
        return ResponseEntity.ok(shortURL.toString());
    }
}
