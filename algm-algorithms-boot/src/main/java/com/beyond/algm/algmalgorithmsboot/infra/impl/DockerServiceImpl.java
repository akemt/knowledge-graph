package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.DockerService;
import com.beyond.algm.exception.AlgException;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/24 8:50
 */
@Service
@Slf4j
public class DockerServiceImpl implements DockerService {
    @Autowired
    private DockerClient dockerClient;

    @Value("${harbor.host}")
    private String harborHost;

    @Value("${harbor.projectName}")
    private String projectName;

    public String bulidDockerImage(String modId, String usrCode, String version) throws AlgException{
        // 结果举例 192.168.1.92:9443/algmarket/erniu3_testjava:0.0.3
        String tag = harborHost + "/" +projectName+"/" + usrCode + "_" + modId.toLowerCase() + ":" + version;
        log.debug("build image tag :{}",tag);
        Set tags = new HashSet<String>();
        tags.add(tag);
        String imageId = dockerClient.buildImageCmd(new File("/work/docker_images", "Dockerfile")).withTags(tags).withNoCache(true).exec(new BuildImageResultCallback()).awaitImageId();
        log.debug("build image id:{}",imageId);
        return imageId;
    }


    public void pullDockerImage() throws AlgException{
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

    }
}
