package com.example.demo.config;

import org.springframework.beans.factory.annotation.Configurable;

import org.springframework.context.annotation.Bean;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configurable
@EnableSwagger2
public class swaggerUI {
	
	   @Bean
	    public Docket productApi() {
	       return new Docket(DocumentationType.SWAGGER_2).select()
	          .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller")).build();
	    }

		/*
		 * @Bean public Docket api() { return new Docket(DocumentationType.SWAGGER_2)
		 * .select() .apis(RequestHandlerSelectors.any()) .paths(PathSelectors.any())
		 * .build(); }
		 */

	    private ApiInfo apiEndPointsInfo() {
	        return new ApiInfoBuilder().title("Spring Boot REST API")
	                                   .description("Employee Management REST API")
	                                   .contact(new Contact("loda", "https://loda.me/", "loda.namnh@gmail.com"))
	                                   .license("Apache 2.0")
	                                   .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	                                   .version("1.0.0")
	                                   .build();
	    }
	    
	 
}
