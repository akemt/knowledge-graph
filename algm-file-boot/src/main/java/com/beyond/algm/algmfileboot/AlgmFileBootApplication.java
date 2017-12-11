package com.beyond.algm.algmfileboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.beyond.algm.mapper")
public class AlgmFileBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgmFileBootApplication.class, args);
	}
}
