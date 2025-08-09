package com.adarsh.RealQuizzApp.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class WebConfig {

    @Value("${frontend.url}")
    private String Frontend_url;

    @Value("${frontendtest.url}")
    private String Frontendtest;

    @Bean
    public CorsFilter corsFilter() {



        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("http://localhost:5173"); // Allow your frontend origin
//        config.addAllowedOrigin("http://localhost:5173/test"); // Allow your frontend origin
        config.setAllowedOrigins(List.of(Frontend_url , Frontendtest));
//        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setAllowedHeaders(List.of("*")); // Allow all headers
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        config.addAllowedHeader("*"); // Allow all headers
//        config.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.)
        config.setAllowCredentials(true); // Allow cookies/auth headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply to all endpoints

        return new CorsFilter(source);
    }
}
