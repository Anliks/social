package com.example.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
     private String uploadPath;

  //  @Override
  //  public void addResourceHandlers(ResourceHandlerRegistry registry) {
  //      registry.addResourceHandler("/mp3/**").addResourceLocations("file://" + uploadPath + "/");
  //  }
}
