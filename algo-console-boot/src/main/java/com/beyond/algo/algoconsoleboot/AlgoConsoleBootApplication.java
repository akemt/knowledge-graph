package com.beyond.algo.algoconsoleboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@EnableTransactionManagement
@EnableResourceServer
@MapperScan(basePackages = "com.beyond.algo.mapper")
public class AlgoConsoleBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoConsoleBootApplication.class, args);
	}
}

