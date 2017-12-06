package com.beyond.algm.algmquartzboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.beyond.algm.mapper")
public class AlgmQuartzBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgmQuartzBootApplication.class, args);
	}
}
