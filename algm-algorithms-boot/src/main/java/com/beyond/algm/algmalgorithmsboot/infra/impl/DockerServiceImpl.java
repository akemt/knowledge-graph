package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.DockerService;
import com.beyond.algm.exception.AlgException;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/24 8:50
 */
@Service
@Slf4j
public class DockerServiceImpl implements DockerService {

    public void makeDockerImage() throws AlgException{
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                //主机URL，例如tcp://localhost:2376或unix:///var/run/docker.sock
                .withDockerHost("tcp://192.168.1.94:2375")
                //启用/禁用TLS验证（切换http和https协议）
                .withDockerTlsVerify(false)
                //.withDockerCertPath("/home/user/.docker/certs")
                //额外的docker配置文件路径
                //.withDockerConfig("/home/user/.docker")
                .withApiVersion("1.23")
                /*.withRegistryUrl("http://192.168.1.94:2375/v1/")
                .withRegistryUsername("admin")
                .withRegistryPassword("Harbor12345")
                .withRegistryEmail("admin@example.com")*/
                .build();
        dockerClient.buildImageCmd(new File("E:/dockerfile", "Dockerfile")).withNoCache(true).exec(new BuildImageResultCallback()).awaitImageId();
    }
}
