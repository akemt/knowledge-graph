package com.beyond.algo.algogitboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.beyond.algo.controller","com.beyond.algo.infra","com.beyond.algo.model"})
@MapperScan(basePackages = "com.beyond.algo.mapper")
public class AlgoGitBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoGitBootApplication.class, args);
	}
}
