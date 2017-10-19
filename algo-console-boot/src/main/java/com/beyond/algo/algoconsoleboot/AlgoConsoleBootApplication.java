package com.beyond.algo.algoconsoleboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages={"com.beyond.algo.controller","com.beyond.algo.infra","com.beyond.algo.model"})
@MapperScan(basePackages = "com.beyond.algo.mapper")
public class AlgoConsoleBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoConsoleBootApplication.class, args);
	}
}

