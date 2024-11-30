package com.shortify.Shortify_Url_Shortner.controller;

import com.shortify.Shortify_Url_Shortner.exceptions.UrlNotFoundException;
import com.shortify.Shortify_Url_Shortner.model.ShortenedURL;
import com.shortify.Shortify_Url_Shortner.repository.ShortenedUrlRepository;
import com.shortify.Shortify_Url_Shortner.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    @GetMapping("/redirect")
    public ResponseEntity<Void> redirectToOriginalURL(@RequestParam("shortURL")String shortURL){
        try{
            String originalURL= urlShortenerService.getOriginalURL(shortURL);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalURL)).build();
        }catch(UrlNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
