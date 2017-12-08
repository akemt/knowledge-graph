package com.beyond.algm.algmalgorithmsboot.config;

import com.beyond.algm.exception.AlgException;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/8 16:13
 */
@SpringBootConfiguration
@Slf4j
public class K8sConfig {
    @Value("${k8s.host}")
    private String k8sHost;

    @Bean
    public KubernetesClient getKubernetesClient() throws AlgException{
        Config config = new ConfigBuilder().withMasterUrl(k8sHost).build();
        KubernetesClient client = new DefaultKubernetesClient(config);
        return client;
    }

}
