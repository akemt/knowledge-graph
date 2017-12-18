package com.beyond.algm.algmhbaseboot.infra;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;

import java.sql.*;
import java.util.Set;

/**
 * Phoenix Runner
 * create by JR.Elephant on 2017/12/18
 * */

@SpringBootConfiguration
@Slf4j
public class PhoenixRunner {

    @Value("${phoenix.zkHosts}")
    private String zkHosts;

    private Connection connection;
    private Statement statement;

    /**
     * initialize
     * */
    public PhoenixRunner(){
        // phoenix驱动
        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 建立与hbase的连接
        try {
            connection = DriverManager.getConnection(zkHosts);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * close all connection
     * */
    public void closeAll() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * insert table by Json
     * */
    public void insertByJson(JSONObject obj) throws SQLException {
        // 各种串号
        String alg_sn = "-1";
        String umc_sn = "-1";
        String mod_sn = "-1";
        String ver_sn = "-1";
        String call_usr_sn = "-1";
        String owner_usr_sn = "-1";
        // 各种使用信息
        long duration = 0;
        int billed_call_cnt = 0;
        int call_pay_amount = 0;
        int mod_call_cnt = 0;
        int is_charging = 0;
        long start_time = 0;
        long end_time = 0;

        // 将json解析赋值给变量
        Set<String> keySet = obj.keySet();
        for (String key:keySet) {
            switch(key)
            {
                // 各种串号
                case "alg_sn":
                    alg_sn = obj.getString(key);
                    break;
                case "umc_sn":
                    umc_sn = obj.getString(key);
                    break;
                case "mod_sn":
                    mod_sn = obj.getString(key);
                    break;
                case "ver_sn":
                    ver_sn = obj.getString(key);
                    break;
                case "call_usr_sn":
                    call_usr_sn = obj.getString(key);
                    break;
                case "owner_usr_sn":
                    owner_usr_sn = obj.getString(key);
                    break;
                // 各种使用信息
                case "duration":
                    duration = Long.parseLong(obj.getString(key));
                case "billed_call_cnt":
                    billed_call_cnt = Integer.parseInt(obj.getString(key));
                case "call_pay_amount":
                    call_pay_amount = Integer.parseInt(obj.getString(key));
                case "mod_call_cnt":
                    mod_call_cnt = Integer.parseInt(obj.getString(key));
                case "is_charging":
                    is_charging = Integer.parseInt(obj.getString(key));
                case "start_time":
                    start_time = Long.parseLong(obj.getString(key));
                case "end_time":
                    end_time = Long.parseLong(obj.getString(key));
            }
        }
        // sql语句
        String sql = "UPSERT INTO MYTEST(ALGSN,TEST1,TEST2,TEST3,TEST4) VALUES ("
                + "'" + alg_sn + "'" + ","
                + "'" + umc_sn + "'" + ","
                + "'" + mod_sn + "'" + ","
                + "'" + ver_sn + "'" + ","
                + "'" + call_usr_sn + "'" + ","
                + "'" + owner_usr_sn + "'" + ","
                + duration + ","
                + billed_call_cnt + ","
                + call_pay_amount + ","
                + mod_call_cnt + ","
                + is_charging + ","
                + start_time + ","
                + end_time + ","
                ;
    }

    public void query (){

    }

}
