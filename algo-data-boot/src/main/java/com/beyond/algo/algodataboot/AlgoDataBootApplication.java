package com.beyond.algo.algodataboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.beyond.algo.mapper")
public class AlgoDataBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoDataBootApplication.class, args);
	}
}
