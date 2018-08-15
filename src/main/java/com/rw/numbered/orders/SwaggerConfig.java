package com.rw.numbered.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rw.numbered.orders"))
                .paths(regex("/v1/numbered/orders.*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Сервис работы с ЭПД на поезда с нумерованными местами (для пользователей)")
                .description("Сервис включает в себя список операций с ЭПД, заказами и корзиной заказов на поезда с нумерованными местами для пользователей СППД")
                .version("v1")
                .license("")
                .licenseUrl("")
                .build();
    }
}