package com.beyond3.yyGang.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.swagger-ui.version}") String springdocVersion) {

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearer");

        Info info = new Info()
                .title("타이틀 입력")
                .version(springdocVersion)
                .description("API에 대한 설명 부분");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearer", securityScheme))
                .info(info)
                .addSecurityItem(securityRequirement)
                .schemaRequirement("bearer",securityScheme);
    }


}