package com.beyond.algm.algmalgorithmsboot.util;



import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.beyond.algm.algmalgorithmsboot.model.CephConfigModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


/**
 * @author ：zhangchuanzhi
 * @Description:ceph连接
 * @date ：9:15 2017/11/30
 */
public class CephUtil {
    @Value("${ceph.host}")
    private  String host;
    @Value("${ceph.accessKey}")
    private   String accessKey;
    @Value("${ceph.secretKey}")
    private  String secretKey;

    @Autowired
    private  CephConfigModel cephConfigModel;
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
