package com.beyond.algo.algoalgorithmsboot.infra.impl;

import com.beyond.algo.algoalgorithmsboot.model.ProjectConfigModel;
import com.beyond.algo.algoalgorithmsboot.model.PublishConfigModel;
import com.beyond.algo.common.FileUtil;
import com.beyond.algo.algoalgorithmsboot.infra.PublishService;
import com.beyond.algo.algoalgorithmsboot.util.FreemarkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private PublishConfigModel publishConfigModel;
    @Autowired
    private ProjectConfigModel projectConfigModel;

    @Override
    public void initBootProject(String username, String projectName, String projectDescription, String algoVersion) throws Exception {
        // 获取根目录
        FileUtil.createDir(publishConfigModel.getLocalBasePath());

        // 以用户名作为用户子目录名
        String userPath = publishConfigModel.getLocalBasePath() + File.separator + username;
        FileUtil.createDir(userPath);

        // 以项目名作为项目子目录名
        String projectPath = userPath + File.separator + projectName;
        FileUtil.createDir(projectPath);

        // 创建一个空的dist文件夹
        FileUtil.createDir(projectPath + File.separator + publishConfigModel.getDistFolder());

        // 拼装参数
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("projectName", projectName);
        paramMap.put("projectDescription", projectDescription);
        paramMap.put("algoVersion", algoVersion);
        paramMap.put("javaVersion", publishConfigModel.getJavaVersion());
        paramMap.put("distFolder", publishConfigModel.getDistFolder());
        paramMap.put("packetName", projectConfigModel.getPackageName());

        String templatePath = new ClassPathResource("templates/boot/java").getFile().getPath();
        // 生成pom.xml文件
        FreemarkerUtil.createFile(templatePath, "pom.xml.ftl", projectPath, "pom.xml", paramMap);

        // 生成src基本结构
        String srcPath = projectPath + File.separator + "src";
        FileUtil.createDir(srcPath);
        String testPath = srcPath + File.separator + "test";
        FileUtil.createDir(testPath);
        String testJavaPath = testPath + File.separator + "java";
        FileUtil.createDir(testJavaPath);

        String mainPath = projectPath + File.separator + "main";
        FileUtil.createDir(mainPath);
        String mainResourcePath = mainPath + File.separator + "resources";
        FileUtil.createDir(mainResourcePath);
        String appFileName = mainResourcePath + File.separator + "application.properties";
        FileUtil.createDir(appFileName);

        String mainJavaPath = mainPath + File.separator + "java";
        FileUtil.createDir(mainJavaPath);

        // 生成boot运行主类
        String comPath = mainJavaPath + File.separator + "com";
        FileUtil.createDir(comPath);
        String packetPath = comPath + File.separator + projectConfigModel.getPackageName();
        FileUtil.createDir(packetPath);
        String packetUserPath = packetPath + File.separator + username;
        FileUtil.createDir(packetUserPath);
        FreemarkerUtil.createFile(templatePath, "Application.java.ftl", packetUserPath, "Application.java", paramMap);
        String controllerPath = packetUserPath + File.separator + "controller";
        FileUtil.createDir(controllerPath);
        FreemarkerUtil.createFile(templatePath, "MainController.java.ftl", controllerPath, "MainController.java", paramMap);
    }
}
