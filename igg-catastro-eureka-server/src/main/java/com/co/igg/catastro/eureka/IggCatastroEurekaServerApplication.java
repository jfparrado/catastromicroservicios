package com.co.igg.catastro.eureka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class IggCatastroEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IggCatastroEurekaServerApplication.class, args);
	}

}
