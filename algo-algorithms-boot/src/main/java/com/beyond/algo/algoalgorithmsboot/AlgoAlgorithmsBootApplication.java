package com.beyond.algo.algoalgorithmsboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableResourceServer
@MapperScan(basePackages = "com.beyond.algo.mapper")
public class AlgoAlgorithmsBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoAlgorithmsBootApplication.class, args);
	}
}

