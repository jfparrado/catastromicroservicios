package com.co.igg.catastro.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class IggCatastroOauthApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	public static void main(String[] args) {
		SpringApplication.run(IggCatastroOauthApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		String password = "12345";
		
		for (int i = 0; i < 4; i++) {
			String passwordBCrypt = passwordEncode.encode(password);
			System.out.println(passwordBCrypt);
		}
		
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
