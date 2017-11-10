package com.beyond.algo.algoalgorithmsboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.beyond.algo.mapper")
public class AlgoAlgorithmsBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoAlgorithmsBootApplication.class, args);
	}
}

