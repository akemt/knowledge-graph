package com.beyond.algm.algmdataboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.beyond.algm.mapper")
public class AlgmDataBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgmDataBootApplication.class, args);
	}
}
