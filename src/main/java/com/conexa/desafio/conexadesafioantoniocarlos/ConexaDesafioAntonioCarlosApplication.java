package com.conexa.desafio.conexadesafioantoniocarlos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ConexaDesafioAntonioCarlosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConexaDesafioAntonioCarlosApplication.class, args);
	}

}
