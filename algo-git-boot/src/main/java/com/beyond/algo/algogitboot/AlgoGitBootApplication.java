package com.beyond.algo.algogitboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.beyond.algo.controller","com.beyond.algo.infra","com.beyond.algo.infra.impl","com.beyond.algo.model"})
public class AlgoGitBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoGitBootApplication.class, args);
	}
}
