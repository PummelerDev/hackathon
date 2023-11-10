package com.hackathon.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI(final Info info) {
    return new OpenAPI()
        .info(info);
  }

  @Bean
  public Info info() {
    return new Info()
        .title("Backend API")
        .version("v1")
        .description("Contain endpoints on hackathon team.");
  }
}
