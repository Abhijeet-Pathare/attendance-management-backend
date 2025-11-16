package com.codesoft.app.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Codesoft Attendance API",
                version = "1.0",
                description = "API documentation for Attendance Management System"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Authorization header using the Bearer scheme",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi adminApis() {
        return GroupedOpenApi.builder()
                .group("Admin APIs")
                .pathsToMatch("/students/**", "/batches/**","/auth/**",
                        "/attendance/report/**","/attendance/**")
                .build();
    }

    @Bean
    public GroupedOpenApi attendanceApis() {
        return GroupedOpenApi.builder()
                .group("Attendance APIs")
                .pathsToMatch("/attendance/**")
                .build();
    }

    @Bean
    public GroupedOpenApi authApis() {
        return GroupedOpenApi.builder()
                .group("Authentication APIs")
                .pathsToMatch("/auth/**")
                .build();
    }
}
