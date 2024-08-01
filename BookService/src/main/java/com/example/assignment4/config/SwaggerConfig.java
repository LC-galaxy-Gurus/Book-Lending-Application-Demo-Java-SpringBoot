package com.example.assignment4.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import springfox.documentation.service.ApiInfo;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class SwaggerConfig {


    private ApiInfo apiInfo() {
        return new ApiInfo(apiInfo().getTitle(), apiInfo().getDescription(), null,null,null,null,null, Collections.emptyList());
    }
    @Bean
    public OpenAPI registrationOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Swagger API Docs Howtofixthebugs")
                        .description("Howtofixthebugs API Description")
                        .version("1.0"));
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
