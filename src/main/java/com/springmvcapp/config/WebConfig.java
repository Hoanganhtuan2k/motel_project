package com.springmvcapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // Để phục vụ ảnh từ thư mục UPLOAD_DIR
    registry.addResourceHandler("/uploads/**")
        .addResourceLocations("file:/path/to/your/upload/directory/");
  }
}
