package com.stackroute.favouriteservice.config;

import com.google.common.base.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/*As in this class we are implementing Swagger So annotate the class with @Configuration and 
 * @EnableSwagger2
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/*
	 * Annotate this method with @Bean . This method will return an Object of Docket.
	 * This method will implement logic for swagger
	 */
    @Bean
    public Docket productApi() {
    	return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.stackroute.favouriteservice.controller")).paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Favourite API")
				.description("Favourite Service for developers")
				.termsOfServiceUrl("http://stackroute.in")
				.contact("nachande@in.ibm.com").license("Stackroute License")
				.licenseUrl("nares.chander@gmail.com").version("1.0").build();
	}

    
    private Predicate<String> postPath() {
    	return regex("/api/");
    }
}
