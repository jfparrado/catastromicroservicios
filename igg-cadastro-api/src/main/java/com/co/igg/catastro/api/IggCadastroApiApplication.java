package com.co.igg.catastro.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.co.igg.catastro.api.Constants;

//@EnableEurekaClient
@EnableFeignClients
@EntityScan({"com.co.igg.catastro.common.models"})
@EnableJpaRepositories("com.co.igg.catastro.api.persistence.dao")
@SpringBootApplication
public class IggCadastroApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(IggCadastroApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(Constants.API_URL)
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}