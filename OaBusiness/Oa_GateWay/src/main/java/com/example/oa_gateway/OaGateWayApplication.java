package com.example.oa_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.util.pattern.PathPatternParser;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class OaGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaGateWayApplication.class, args);
    }

}
