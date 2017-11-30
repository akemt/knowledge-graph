package com.beyond.algm.algmalgorithmsboot.config;

import com.beyond.algm.exception.AlgException;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/29 8:19
 */
@SpringBootConfiguration
@Slf4j
public class DockerConfig {
    @Value("${docker.host}")
    private String dockerHost;
    @Value("${docker.tls.verify}")
    private Boolean dockerTlsVerify;

    @Bean
    public DockerClient getDockerClient() throws AlgException {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerHost)
                .withDockerTlsVerify(dockerTlsVerify)
                /*.withRegistryUrl("http://192.168.1.94:2375/v1/")
                .withRegistryUsername("admin")
                .withRegistryPassword("Harbor12345")
                .withRegistryEmail("admin@example.com")*/
                .build();

        DockerCmdExecFactory dockerCmdExecFactory = new JerseyDockerCmdExecFactory()
                .withReadTimeout(100000)
                .withConnectTimeout(1000)
                .withMaxTotalConnections(100)
                .withMaxPerRouteConnections(10);

        DockerClient dockerClient = DockerClientBuilder.getInstance(config)
                .withDockerCmdExecFactory(dockerCmdExecFactory)
                .build();

        return dockerClient;
    }
}
