package com.school.main.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("main")
                .pathsToMatch("/**")
                .packagesToScan("com.school.main")
                .build();
    }

    @Bean
    public OpenAPI forumAluraOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("School project - Classroom")
                                .description("Learning project DARM")
                                .version("v0.0.1")
                                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .components(new Components().addSecuritySchemes("bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                                                                                      .scheme("bearer")
                                                                                                      .bearerFormat("JWT")))
                .components(new Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("bearer").in(SecurityScheme.In.HEADER)
                                .bearerFormat("JWT")));
    }
}
