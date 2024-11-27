package com.shortify.Shortify_Url_Shortner.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "shortened_urls")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShortenedURL {
    @Id
    private String id;
    private String originalURL;
    private String shortURL;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
