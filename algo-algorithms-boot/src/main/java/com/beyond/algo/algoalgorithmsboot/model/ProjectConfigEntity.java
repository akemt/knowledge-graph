package com.beyond.algo.algoalgorithmsboot.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：zhangchuanzhi
 * @Description:密码公钥
 * @date ：9:50 2017/9/27
 */
@Component
@ConfigurationProperties(prefix="pwencryp")
@Data
public class ProjectConfigEntity {
    //private String password;
    private String keyDES;
    private String keyAES;
}
