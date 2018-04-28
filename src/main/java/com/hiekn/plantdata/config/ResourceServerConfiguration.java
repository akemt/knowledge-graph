//package com.hiekn.plantdata.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
///**
// * @Author: qihe
// * @Description:
// * @Date: create in 2017/11/10 10:45
// */
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers(/*"/v1/algm/{usrCode}/{modId}/{version:.+}",
//                        "/v1/data/{usrCode}/{dataname}/{dataSet}/{fileName:.+}"*/
//                ).permitAll().and()
//                .authorizeRequests().anyRequest().authenticated();
//    }
//}
