package com.beyond.algo.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="gitlib")
@Data
public class GitConfigModel {
    private String baseUrl;
    private String rootName;
    private String rootPasswd;
    private String privateToken;
    private String localBasePath;

}
