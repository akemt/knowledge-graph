package com.beyond.algm.algmfileboot.util;



import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;


/**
 * @author ：zhangchuanzhi
 * @Description:ceph连接
 * @date ：9:15 2017/11/30
 */
public class CephUtil {
    public static AmazonS3 connectCeph(String accessKey,String secretKey,String host ){
     AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);
        // 方法废弃后期优化
        AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);
        conn.setEndpoint(host);
        return conn;
    }

}
