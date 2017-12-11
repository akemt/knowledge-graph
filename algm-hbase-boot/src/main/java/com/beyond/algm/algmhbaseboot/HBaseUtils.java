package com.beyond.algm.algmhbaseboot;

import com.alibaba.fastjson.JSONObject;
import com.beyond.algm.common.Assert;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * HBase 工具类
 * create by JR.Elephant on 2017/12/1
 * */
public class HBaseUtils {

    private static final Logger logger = LoggerFactory.getLogger(HBaseUtils.class);

    private static Configuration conf;
    private static Connection connection;
    private static Table table;
    private static Put put;

    private static Set<String> keySet;
    private static Iterator<String> itor;

    /**
     * initialize conf connection admin
     * */
    static {
        // Initialize the HBaseConfiguration instance if it doesn't exists
        if (conf == null) {
            // Configuration
            try {
                conf = HBaseConfiguration.create();
                conf.set("hbase.zookeeper.property.clientPort", SiteAndParameter.zkPort);
                conf.set("hbase.zookeeper.quorum", SiteAndParameter.zkHosts);
                conf.set("hbase.master", SiteAndParameter.hbaseMaster);
            } catch (Exception e) {
                logger.error("HBase Configuration Initialization failure !");
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 获取hbase的链接
     * */
    public static synchronized Connection getConnection(){
        // Initialize the Connection instance if it doesn't exists
        try {
            if (connection == null || connection.isClosed()){
                connection = ConnectionFactory.createConnection(conf);
            }
//            System.out.println("---------------" + connection.hashCode());
        } catch (Exception e) {
            logger.error("HBase 建立链接失败！", e);
        }
        return connection;
    }

    /**
     * close the Connection and Admin
     * */
    public static void closeCA(Connection connection, Admin admin) throws IOException {
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
    public static void createTable(String tableName,String columnFamily) throws IOException {
        // 获取hbase连接以及创建hbase admin
        Connection conn = getConnection();
        Admin admin = conn.getAdmin();
        try {
            //判断表是否存在
            TableName tn = TableName.valueOf(tableName);
            if (admin.tableExists(tn)) {
                logger.warn("Table: {} is exists!", tableName);
                return;
            }
            //定义表结构
            HTableDescriptor htd = new HTableDescriptor(tn);

            HColumnDescriptor hcd1 = new HColumnDescriptor(columnFamily);
            hcd1.setBlockCacheEnabled(SiteAndParameter.blockCacheEnabled);
            hcd1.setInMemory(SiteAndParameter.inMemory);
            hcd1.setMaxVersions(SiteAndParameter.maxVersion);

            htd.addFamily(hcd1);
            //建表
            admin.createTable(htd);
            logger.info("Table: {} create success!", tableName);

        } finally {
            //close the connection and admin of HBase
            closeCA(conn, admin);
        }
    }

    /**
     * delete table
     * */
    public static void deleteTable(String tableName) throws IOException {
        //获取hbase连接以及创建hbase admin
        Connection conn = getConnection();
        Admin admin = conn.getAdmin();
        try {
            TableName tn = TableName.valueOf(tableName);
            if(!admin.tableExists(tn)){
                logger.warn("Table: {} is not exists!", tableName);
                return;
            }
            //使表不可用，然后删表
            admin.disableTable(tn);
            admin.deleteTable(tn);
            logger.info("Table: {} delete success!", tableName);

        } finally {
            closeCA(conn, admin);
        }
    }

    /**
     * create table snapshot
     * */
    public static void createTableSnapshot(String snapshotName, String tableName){
        try {
            Admin admin = getConnection().getAdmin();
            admin.snapshot(snapshotName, TableName.valueOf(tableName));
            logger.info("Snapshot " + snapshotName + " create success !");
        } catch (IOException e) {
            logger.error("Snapshot " + snapshotName + " create failed !", e);
        }
    }

    /**
     * delete table snapshot
     * */
    public static void deleteSnapshot(String snapshotName){
        try {
            Admin admin = getConnection().getAdmin();
            if(Assert.isNotEmpty(snapshotName))
                admin.deleteSnapshot(snapshotName);
            else
                logger.error("SnapshotName can't be null !");
        } catch (Exception e) {
            logger.error("Snapshot " + snapshotName + " del failed !", e);
        }
    }

    /**
     * insert table by Json
     * */
    public void insertTableByJson(String tableName, JSONObject obj) throws IOException {
        // Establish the HBase connection
        connection = getConnection();
        table = connection.getTable(TableName.valueOf(tableName));
        // Get the KeySet from jsonObject
        keySet = obj.keySet();

        //----------------------test3------------------------
        System.out.println(obj.toString());
        //--------------------------------------------------
        //-----------------有问题

        //设置put获取rowkey
        put = new Put(Bytes.toBytes(obj.getString(SiteAndParameter.rowkey1)));
        //删除json中的rowkey元素
        itor = keySet.iterator();
        for (int i = 0; i < keySet.size(); i++){
            String name = itor.next();
            if (SiteAndParameter.rowkey1.equals(name)){
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
