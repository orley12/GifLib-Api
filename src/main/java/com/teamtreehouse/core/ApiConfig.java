package com.teamtreehouse.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ApiConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("called!!!".toUpperCase());
        registry.addResourceHandler("/files/**")
                .addResourceLocations("FILE:///C:/USERS/SOLARIN%20O.%20OLUBAYODE/DESKTOP/IdeaProjects/GifLib-Api/UPLOAD-DIR/");
    }
}
