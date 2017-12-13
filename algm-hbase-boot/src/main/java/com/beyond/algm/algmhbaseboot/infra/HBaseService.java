package com.beyond.algm.algmhbaseboot.infra;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;

public interface HBaseService {

    /**
     *
     * @param connection 连接器
     * @param admin hbase表管理员
     * @throws IOException
     */
    void closeCA(Connection connection, Admin admin) throws IOException;

    /**
     *
     * @param tableName hbase表名
     * @param columnFamily 列族名
     * @throws IOException
     */
    void createTable(String tableName,String columnFamily) throws IOException;

    /**
     *
     * @param tableName hbase表名
     * @throws IOException
     */
    void deleteTable(String tableName) throws IOException;

    /**
     *
     * @param snapshotName hbase快照名
     * @param tableName hbase表名
     */
    void createTableSnapshot(String snapshotName, String tableName);

    /**
     *
     * @param snapshotName hbase快照名
     */
    void deleteSnapshot(String snapshotName);

    /**
     *
     * @param tableName hbase 表名
     * @param obj 插入的数据
     * @throws IOException
     */
    void insertTableByJson(String tableName, JSONObject obj) throws IOException;
}
