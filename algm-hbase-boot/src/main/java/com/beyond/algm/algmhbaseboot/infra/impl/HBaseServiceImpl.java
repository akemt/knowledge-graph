package com.beyond.algm.algmhbaseboot.infra.impl;

import com.alibaba.fastjson.JSONObject;
import com.beyond.algm.algmhbaseboot.infra.HBaseService;
import com.beyond.algm.common.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

@Service
@Slf4j
public class HBaseServiceImpl implements HBaseService {

    @Autowired
    private Connection connection;
    @Value("${hbase.rowkey1}")
    private String rowkey1;

    @Value("${hbase.blockCacheEnabled}")
    private boolean blockCacheEnabled;
    @Value("${hbase.inMemory}")
    private boolean inMemory;
    @Value("${hbase.tableName1}")
    private String tableName1;
    @Value("${hbase.maxVersion}")
    private int maxVersion;

    /**
     * close the Connection and Admin
     * */
    public void closeCA(Connection connection, Admin admin) throws IOException {
        if (admin != null){
            admin.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * create table
     * */
    public  void createTable(String tableName,String columnFamily) throws IOException {
        // 获取hbase连接以及创建hbase admin
        Admin admin = connection.getAdmin();
        try {
            //判断表是否存在
            TableName tn = TableName.valueOf(tableName);
            if (admin.tableExists(tn)) {
                log.warn("Table: {} is exists!", tableName);
                return;
            }
            //定义表结构
            HTableDescriptor htd = new HTableDescriptor(tn);

            HColumnDescriptor hcd1 = new HColumnDescriptor(columnFamily);
            hcd1.setBlockCacheEnabled(blockCacheEnabled);
            hcd1.setInMemory(inMemory);
            hcd1.setMaxVersions(maxVersion);

            htd.addFamily(hcd1);
            //建表
            admin.createTable(htd);
            log.info("Table: {} create success!", tableName);

        } finally {
            //close the connection and admin of HBase
            closeCA(connection, admin);
        }
    }

    /**
     * delete table
     * */
    public void deleteTable(String tableName) throws IOException {
        //获取hbase连接以及创建hbase admin
        Admin admin = connection.getAdmin();
        try {
            TableName tn = TableName.valueOf(tableName);
            if(!admin.tableExists(tn)){
                log.warn("Table: {} is not exists!", tableName);
                return;
            }
            //使表不可用，然后删表
            admin.disableTable(tn);
            admin.deleteTable(tn);
            log.info("Table: {} delete success!", tableName);

        } finally {
            closeCA(connection, admin);
        }
    }

    /**
     * create table snapshot
     * */
    public void createTableSnapshot(String snapshotName, String tableName){
        try {
            Admin admin = connection.getAdmin();
            admin.snapshot(snapshotName, TableName.valueOf(tableName));
            log.info("Snapshot " + snapshotName + " create success !");
        } catch (IOException e) {
            log.error("Snapshot " + snapshotName + " create failed !", e);
        }
    }

    /**
     * delete table snapshot
     * */
    public void deleteSnapshot(String snapshotName){
        try {
            Admin admin = connection.getAdmin();
            if(Assert.isNotEmpty(snapshotName))
                admin.deleteSnapshot(snapshotName);
            else
                log.error("SnapshotName can't be null !");
        } catch (Exception e) {
            log.error("Snapshot " + snapshotName + " del failed !", e);
        }
    }

    /**
     * insert table by Json
     * */
    public void insertTableByJson(String tableName, JSONObject obj) throws IOException {
        // Establish the HBase connection
        Table table = connection.getTable(TableName.valueOf(tableName));
        // Get the KeySet from jsonObject
        Set<String> keySet = obj.keySet();

        //设置put获取rowkey
        Put put = new Put(Bytes.toBytes(obj.getString(rowkey1)));
        //删除json中的rowkey元素
        Iterator<String> itor = keySet.iterator();
        for (int i = 0; i < keySet.size(); i++){
            String name = itor.next();
            if (rowkey1.equals(name)){
                itor.remove();
                i--;
            }

        }
        //将数据写入表
        for (String key:keySet) {
            put.addColumn(Bytes.toBytes("cf1"),
                    Bytes.toBytes(key),
                    Bytes.toBytes(obj.getString(key)));
        }
        table.put(put);
        table.close();
    }
}
