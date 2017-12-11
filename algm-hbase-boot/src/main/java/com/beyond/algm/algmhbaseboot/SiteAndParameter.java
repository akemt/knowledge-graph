package com.beyond.algm.algmhbaseboot;

/**
 * Site and Parameters
 * create by JR.Elephant on 2017/12/4
 * */
public class SiteAndParameter {
    //HBase Master
    public static final String hbaseMaster = "192.168.1.70:6000";
    //HBase MaxVersion
    public static final int maxVersion = 1;
    //HBase BlockCacheEnabled
    public static final boolean blockCacheEnabled = true;
    //HBase InMemory
    public static final boolean inMemory = true;
    //zookeeper hosts
    public static final String zkHosts = "192.168.1.70,192.168.1.71,192.168.1.72";
    //zookeeper port
    public static final String zkPort = "2181";
    //rocketMQ nameServerAddr
    public static final String rocketMQNameSrvAddr = "192.168.1.70:9876;192.168.1.71:9876";
    // 表1 相关属性
    public static final String tableName = "mytest";
    public static final String rowkey1 = "algSn";
    public static final String rocketMQGroup1 = "group_p_no1";
    public static final String rocketMQTopic1 = "Topic_no1";
}
