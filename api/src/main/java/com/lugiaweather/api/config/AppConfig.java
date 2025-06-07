package com.lugiaweather.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
    	  DotEnvConfig.loadEnv();
        return new RestTemplate();
    }
}

