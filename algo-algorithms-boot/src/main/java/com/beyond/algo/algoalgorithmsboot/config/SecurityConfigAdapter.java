package com.beyond.algo.algoalgorithmsboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .logout().and()
                .authorizeRequests()
                .antMatchers( "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
