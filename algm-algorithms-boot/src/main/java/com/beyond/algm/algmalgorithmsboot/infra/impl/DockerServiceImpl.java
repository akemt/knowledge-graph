package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.DockerService;
import com.beyond.algm.algmalgorithmsboot.util.FreemarkerUtil;
import com.beyond.algm.common.Assert;
import com.beyond.algm.exception.AlgException;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import com.github.dockerjava.core.command.PushImageResultCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

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

    public String bulidDockerImage(String modId, String usrCode, String version,String targetPath) throws AlgException{
        // 结果举例 192.168.1.92:9443/algmarket/erniu3_testjava:0.0.3
        String tag = getDockerTag(modId,usrCode,version);
        log.debug("build image tag :{}",tag);
        Set tags = new HashSet<String>();
        tags.add(tag);
        String imageId = dockerClient.buildImageCmd(new File(targetPath, "Dockerfile")).withTags(tags).withNoCache(true).exec(new BuildImageResultCallback()).awaitImageId();
        log.debug("build image id:{}",imageId);
        return imageId;
    }


    public void pushDockerImageToHarbor(String modId, String usrCode, String version) throws AlgException{
        String tag = getDockerTag(modId,usrCode,version);
        try{
            dockerClient.pushImageCmd(tag).exec(new PushImageResultCallback()).awaitSuccess();
        }catch (Exception e){
            log.error("推送镜像失败:",e);
            new AlgException("BEYOND.ALG.MODILE.PUBLISH.0000001", Arrays.asList(""));
        }
    }

    public void makeDockerfile(String active,String lanName,String targetPath,String jarFileName) throws AlgException{
        // 拼装参数
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("jarFileName", jarFileName);
        try {

            String templatePath = null;
            if (Assert.isNotEmpty(active) && active.equals("dev")) {
                try {
                    templatePath = "file:" + new ClassPathResource("/templates/dockerfile").getFile().getPath() + File.separator;
                } catch (Exception e) {
                    log.debug("调试Spring Boot发布取模板失败,错误:{}", e.getMessage());
                }
            } else {
                templatePath = "jar:" + this.getClass().getClassLoader().getResource("/templates/dockerfile/").getPath();
            }
            // 生成pom.xml文件
            FreemarkerUtil.createFile(templatePath, lanName+"-"+"Dockerfile.ftl", targetPath, "Dockerfile", paramMap);
        } catch (Exception e) {
            throw new AlgException(e);
        }
    }

    public String getDockerTag(String modId, String usrCode, String version){
        return harborHost + "/" +projectName+"/" + usrCode + "_" + modId.toLowerCase() + ":" + version;
    }
}
