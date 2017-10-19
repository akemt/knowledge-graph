package com.beyond.algo.algoblogboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AlgoBlogBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoBlogBootApplication.class, args);
	}
}
