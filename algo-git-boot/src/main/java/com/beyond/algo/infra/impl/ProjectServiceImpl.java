package com.beyond.algo.infra.impl;

import com.beyond.algo.common.FileUtil;
import com.beyond.algo.infra.ProjectService;
import com.beyond.algo.model.GitConfigModel;
import com.beyond.algo.model.ProjectConfigModel;
import com.beyond.algo.util.FreemarkerUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private ProjectConfigModel projectConfigModel;

    @Override
    public void initProject(String username, String projectName) throws Exception {
        // 获取根目录
        FileUtil.createDir(gitConfigModel.getLocalBasePath());

        // 以用户名作为用户子目录名
        String userPath = gitConfigModel.getLocalBasePath() + File.separator + username;
        FileUtil.createDir(userPath);

        // 以项目名作为项目子目录名
        String projectPath = userPath + File.separator + projectName;
        FileUtil.createDir(projectPath);

        // 先将直接拷贝的文件复制到目录下
        String templatePath = new ClassPathResource("template/project/java").getFile().getPath();
        String[] cloneFileArr = projectConfigModel.getCloneFiles().split(",");
        for (String cloneFileName : cloneFileArr) {
            File srcFile = new File(templatePath + File.separator + cloneFileName);
            File destFile = new File(projectPath + File.separator + cloneFileName);
            FileUtils.copyFile(srcFile, destFile);
        }

        // 拼装参数
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("projectName", projectName);
        paramMap.put("language", "java");
        paramMap.put("currentTime", new Date().getTime() + "");

        // Freemarker生成配置文件
        String[] ftlFileArr = projectConfigModel.getFtlFiles().split(",");
        for (String ftlFileName : ftlFileArr) {
            String destFileName = ftlFileName.substring(0, ftlFileName.lastIndexOf("."));
            FreemarkerUtil.createFile(templatePath, ftlFileName, projectPath, destFileName, paramMap);
        }

        // 生成算法主类
        String srcPath = projectPath + File.separator + "src";
        FileUtil.createDir(srcPath);
        String packetPath = srcPath + File.separator + projectConfigModel.getPackageName();
        FileUtil.createDir(packetPath);
        String mainClassPath = packetPath + File.separator + projectName;
        FileUtil.createDir(mainClassPath);
        FreemarkerUtil.createFile(templatePath, "Main.java.ftl", mainClassPath, projectName + ".java", paramMap);
    }
}
