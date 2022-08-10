package com.geretq.gerenciadorEstoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class GerenciadorEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorEstoqueApplication.class, args);
	}

}
