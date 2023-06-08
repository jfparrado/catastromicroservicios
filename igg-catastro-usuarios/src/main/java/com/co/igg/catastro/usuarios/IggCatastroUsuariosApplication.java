package com.co.igg.catastro.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableEurekaClient
@EntityScan({"com.co.igg.catastro.common.models"})
@EnableJpaRepositories("com.co.igg.catastro.usuarios.models.dao")
@SpringBootApplication
public class IggCatastroUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(IggCatastroUsuariosApplication.class, args);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1")
                .allowedMethods("*")
                .allowedHeaders("*");
            }
        };
    }
}
