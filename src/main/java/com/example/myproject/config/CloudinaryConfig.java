package com.example.myproject.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cloudinary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CloudinaryConfig {

    private String apiKey;
    private String apiSecret;
    private String cloudName;
}

