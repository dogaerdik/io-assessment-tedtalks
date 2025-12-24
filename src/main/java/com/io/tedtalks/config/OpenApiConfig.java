package com.io.tedtalks.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TedTalks Influence Analysis API")
                        .version("1.0.0")
                        .description("Backend API for managing TedTalks data and analyzing speaker influence")
                        .contact(new Contact()
                                .name("iO Digital")
                                .url("https://iodigital.com")));
    }
}

