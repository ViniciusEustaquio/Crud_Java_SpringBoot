package com.projetoSpring.padroes_projetos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.projetoSpring.padroes_projetos.service")
public class PadroesProjetosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadroesProjetosApplication.class, args);
	}

}
