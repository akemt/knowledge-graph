package com.beyond.algm.algmfileboot;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * @author ：
 * @Description:
 * @date ：11:19 2017/12/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmFileBootApplication.class)
public class Test {

    @org.junit.Test
    public void cephTest() throws Exception {
        String accessKey="BNH0NZDPWGM3JI29ZWVH";
        String secretKey="ajrKdM1xs9TXnDpDt6OZJArCeYBkScfy7mxcYkkL";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);

        AmazonS3 conn = new AmazonS3Client(credentials, clientConfig);

     /*   Region aaa= Region.getRegion(Regions.CN_NORTH_1);*/
        // conn.setRegion(aaa);*//*
        // Boolean abc=conn.doesObjectExist("my-zhang-new-bucket","");
        conn.setEndpoint("192.168.1.80:7480");
        System.out.println("开始创建桶");
      /*  List<Bucket> buckets = conn.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName() + "\t" +bucket.getCreationDate());
        }
        ObjectListing objects = conn.listObjects("my-zhang-new-bucket");
        do {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                System.out.println(objectSummary.getKey() + "\t" +
                        objectSummary.getSize() + "\t");
            }
            objects = conn.listNextBatchOfObjects(objects);
        } while (objects.isTruncated());
        File file = new File("D:/123.jpg");
        System.out.println(file.getName());*/
        Bucket bucket=null;
        String aaaa="my-zhang-new-bucket";
        bucket=conn.createBucket(aaaa);
        File file = new File("D:/123.jpg");

        conn.putObject(aaaa, "abcaaa",file);

        //   Bucket bucket=null;
        conn.setObjectAcl(aaaa, "abcaaa", CannedAccessControlList.PublicRead);
        //     GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest("my-zhang-new-bucket", "abc");
        //    System.out.println(conn.generatePresignedUrl(request));*//*
        System.out.println(conn.getUrl(aaaa,"abcaaa"));
        GeneratePresignedUrlRequest httpRequest=new GeneratePresignedUrlRequest(aaaa, "abcaaa");
        String url=conn.generatePresignedUrl(httpRequest).toString();
        //    Date expires = new Date (new Date().getTime() + 1000 * 60);
        //    GeneratePresignedUrlRequest httpRequest=new GeneratePresignedUrlRequest("zhang/my-zhang-new-bucket", "abc");
        //  httpRequest.setExpiration(expires);
     /*   String url=conn.generatePresignedUrl(httpRequest).toString();
        String url1= java.net.URLDecoder.decode(url,"UTF-8");*/
        //   bucket=conn.createBucket("my-zhang-test-new-bucket");
       /* try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream input = entity.getContent();
            FileOutputStream output = new FileOutputStream("D:/testCeph");
            byte[] buffer=new byte[10240];
            int ch = 0;
            while ((ch = input.read(buffer)) != -1) {
                output.write(buffer,0,ch);
            }
            input.close();
            output.flush();
            output.close();
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(url);
        System.out.println("Connected to the cluster.");*/
    }
}
