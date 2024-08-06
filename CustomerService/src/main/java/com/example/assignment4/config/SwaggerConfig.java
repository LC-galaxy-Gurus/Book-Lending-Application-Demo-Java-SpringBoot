package com.example.assignment4.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import java.util.Collections;

@Configuration
public class SwaggerConfig {


    private ApiInfo apiInfo() {
        return new ApiInfo(apiInfo().getTitle(), apiInfo().getDescription(), null,null,null,null,null, Collections.emptyList());
    }




}
