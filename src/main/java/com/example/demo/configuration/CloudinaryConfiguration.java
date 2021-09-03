package com.example.demo.configuration;

import java.util.Map;

import com.cloudinary.utils.ObjectUtils;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class CloudinaryConfiguration{

    static final Map config= ObjectUtils.asMap(
                "cloud_name", System.getenv("cloud_name"),
                "api_key", System.getenv("api_key"),
                "api_secret", System.getenv("api_secret"),
                "secure", true
            );
}