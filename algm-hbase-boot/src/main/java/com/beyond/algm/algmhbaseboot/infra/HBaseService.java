package com.beyond.algm.algmhbaseboot.infra;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;

public interface HBaseService {

    void closeCA(Connection connection, Admin admin) throws IOException;

    void createTable(String tableName,String columnFamily) throws IOException;

    void deleteTable(String tableName) throws IOException;

    void createTableSnapshot(String snapshotName, String tableName);

    /**
     *
     * @param snapshotName
     */
    void deleteSnapshot(String snapshotName);

    /**
     *
     * @param tableName hbase 表
     * @param obj
     * @throws IOException
     */
    void insertTableByJson(String tableName, JSONObject obj) throws IOException;
}
