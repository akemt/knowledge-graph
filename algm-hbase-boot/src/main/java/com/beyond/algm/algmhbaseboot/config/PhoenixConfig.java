package com.beyond.algm.algmhbaseboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Phoenix Config
 * create by Jr.Elephant on 2017/12/18
 * */
@SpringBootConfiguration
@Slf4j
public class PhoenixConfig {

    @Value("${phoenix.className}")
    private String className;
    @Value("${phoenix.zkHosts}")
    private String zkHosts;

    @Bean
    public Connection getPhoenixConnection(){
        Connection connection = null;
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(zkHosts);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error(" create phoenix connection fail ,please check config className.");
        } catch (SQLException e) {
            log.error(" create phoenix connection fail ,please check zkHost .");
            e.printStackTrace();
        }

        return connection;
    }
}
