package com.beyond.algm.algmhbaseboot.infra;

import com.alibaba.fastjson.JSONObject;

import java.sql.SQLException;

/**
 * Phoenix Service interface
 * create by Jr.Elephant on 2017/12/18
 * */
public interface PhoenixService {

    /**
     * 初始化phoenix的statement
     */
    void initStatement();

    /**
     * 关闭phoenix的statement和connection
     * @throws SQLException
     */
    void closeAll() throws SQLException;

    /**
     * 插入json格式的数据
     * @param obj
     * @throws SQLException
     */
    void insertByJson(JSONObject obj) throws SQLException;

    /**
     * 查询
     */
    void queryAndSendMySQL();
}
