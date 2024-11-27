package com.shortify.Shortify_Url_Shortner.exceptions;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String message){
        super(message);
    }
}
