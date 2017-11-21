package com.beyond.algm.algmblogboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AlgmBlogBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgmBlogBootApplication.class, args);
	}
}
