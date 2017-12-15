package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.MvnService;
import com.beyond.algm.algmalgorithmsboot.infra.PathService;
import com.beyond.algm.algmalgorithmsboot.model.PublishConfigModel;
import com.beyond.algm.common.Assert;
import com.beyond.algm.constant.Constant;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.shared.invoker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/23 11:03
 */
@Service
@Slf4j
public class MvnServiceImpl implements MvnService {

    @Autowired
    private PublishConfigModel publishConfigModel;

    @Autowired
    private PathService pathService;
    private static String mavenHomePath;



    static {
        mavenHomePath = System.getenv("MAVEN_HOME");
        if (Assert.isEmpty(mavenHomePath) || "".equals(mavenHomePath)) {
            mavenHomePath = System.getenv("M2_HOME");
            log.info("maven config is M2_HOME:{}",mavenHomePath);
        }else{
            log.info("maven config is MAVEN_HOME:{}",mavenHomePath);
        }
        if (Assert.isEmpty(mavenHomePath) || "".equals(mavenHomePath)) {
            log.error("Can not get maven env,please install apache maven and configuring environment variables");
        }
    }

    public void mvnPackageMod(String userCode, String modId,String curUsrCode,String isOrg) throws AlgException {
        if (Assert.isEmpty(mavenHomePath) || "".equals(mavenHomePath)) {
            throw new AlgException("发布项目失败，获取不到 maven 路径！");
        }
        InvocationRequest request = new DefaultInvocationRequest();
        String path = pathService.getPublishPath(userCode,modId,curUsrCode,isOrg)+ File.separator + Constant.POM_XML;
        request.setPomFile(new File(path));
        request.setGoals(Arrays.asList("clean", "package -Dmaven.test.skip=true"));
        log.debug("come in maven package request");
        Invoker invoker = new DefaultInvoker();

        invoker.setMavenHome(new File(mavenHomePath));
        try {
            invoker.execute(request);
            InvocationResult result = invoker.execute(request);
            log.debug("come in maven package  is executing");
            if (result.getExitCode() != 0) {
                log.debug("come in maven package  is executed and exitCode is not zero");
                throw new AlgException("BEYOND.ALG.MODILE.PUBLISH.0000001",Arrays.asList(""));
            }
        } catch (MavenInvocationException e) {
            log.debug("come in maven package execute and is failure");
            log.error("publish module maven package is failure ,{}",e);
            throw new AlgException("BEYOND.ALG.MODILE.PUBLISH.0000001",Arrays.asList(""));
        }
    }
}
