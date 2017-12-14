package com.beyond.algm.algmcallboot.call;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/13 12:42
 */
@Slf4j
public class AlgmHttpCall {
    private String usrCode;
    private String modId;
    private String version;
    private HttpPost httpPost;

    public AlgmHttpCall(String usrCode,String modId,String version,String baseUrl,String jsonStr){
        this.usrCode = usrCode;
        this.modId = modId;
        this.version = version;
        httpPost = new HttpPost(baseUrl + usrCode + "/services/" +getServiceName(modId,version) + "/");
        //httpPost = new HttpPost("http://192.168.1.60:8080/api/v1/proxy/namespaces/erniu4/services/erniu4-testjavao1-0-0-3/erniu4/TestJavaO1/0.0.3");
        httpPost.setEntity(new StringEntity(jsonStr,APPLICATION_JSON));
    }

    /**
     *  因为 k8s service 命名规则不允许 . 此处把 . 替换成 -
     * @param modId
     * @param version
     * @return
     */
    private String getServiceName(String modId, String version){
        return modId.toLowerCase()+"-"+version.replaceAll("\\.","-");
    }
    public String send(){
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            log.info("Response content: {}",result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
