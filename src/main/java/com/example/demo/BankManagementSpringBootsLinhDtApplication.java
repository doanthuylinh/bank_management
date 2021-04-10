package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2

@SpringBootApplication
public class BankManagementSpringBootsLinhDtApplication extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BankManagementSpringBootsLinhDtApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BankManagementSpringBootsLinhDtApplication.class);
    }

}
