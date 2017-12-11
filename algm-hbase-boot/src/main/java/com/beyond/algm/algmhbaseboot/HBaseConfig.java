package com.beyond.algm.algmhbaseboot;


import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
@Slf4j
public class HBaseConfig {

    @Value("${hbase.master}")
    private String master;
    @Value("${zookeeper.hosts}")
    private String hosts;
    @Value("${zookeeper.port}")
    private String port;

    private static Configuration conf;
    private static Connection connection;

    @Bean
    public synchronized Connection getHBaseConnection(){
        if (conf == null) {
            try {
                conf = HBaseConfiguration.create();
                conf.set("hbase.zookeeper.property.clientPort", port);
                conf.set("hbase.zookeeper.quorum", hosts);
                conf.set("hbase.master", master);
            } catch (Exception e) {
                log.error("HBase Configuration Initialization failure !");
                throw new RuntimeException(e);
            }
        }
        try {
            if (connection == null || connection.isClosed()){
                connection = ConnectionFactory.createConnection(conf);
            }
        } catch (Exception e) {
            log.error("HBase 建立链接失败！", e);
        }
        return connection;

    }

}
