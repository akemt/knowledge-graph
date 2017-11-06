package com.beyond.algo.algoalgorithmsboot.adapter;

import com.beyond.algo.algoalgorithmsboot.adapter.infra.ModuleAdapter;
import com.beyond.algo.algoalgorithmsboot.model.GitConfigModel;
import com.beyond.algo.algoalgorithmsboot.model.ProjectConfigModel;
import com.beyond.algo.algoalgorithmsboot.util.FreemarkerUtil;
import com.beyond.algo.common.FileUtil;
import com.beyond.algo.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in
 */
@Slf4j
public class JavaModuleAdapter implements ModuleAdapter {

    @Override
    public void createModule(String username, String projectName,GitConfigModel gitConfigModel,ProjectConfigModel projectConfigModel) throws AlgException {
        FileUtil.createDir(gitConfigModel.getLocalBasePath());

        // 以用户名作为用户子目录名
        String userPath = gitConfigModel.getLocalBasePath() + File.separator + username;
        FileUtil.createDir(userPath);

        // 以项目名作为项目子目录名
        String projectPath = userPath + File.separator + projectName;
        FileUtil.createDir(projectPath);

        // 先将直接拷贝的文件复制到目录下
        String templatePath = null;
        try {
            templatePath = new ClassPathResource("templates/project/java").getFile().getPath();
        } catch (IOException e) {
            log.error(e.toString());
            throw new AlgException("");
        }
        String[] cloneFileArr = projectConfigModel.getCloneFiles().split(",");
        for (String cloneFileName : cloneFileArr) {
            File srcFile = new File(templatePath + File.separator + cloneFileName);
            File destFile = new File(projectPath + File.separator + cloneFileName);
            try {
                FileUtils.copyFile(srcFile, destFile);
            } catch (IOException e) {
                log.error(e.toString());
                throw new AlgException("");
            }
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
            try {
                FreemarkerUtil.createFile(templatePath, ftlFileName, projectPath, destFileName, paramMap);
            } catch (Exception e) {
                log.error(e.toString());
                throw new AlgException("");
            }
        }

        // 生成算法主类
        String srcPath = projectPath + File.separator + "src";
        FileUtil.createDir(srcPath);
        String packetPath = srcPath + File.separator + projectConfigModel.getPackageName();
        FileUtil.createDir(packetPath);
        String mainClassPath = packetPath + File.separator + projectName;
        FileUtil.createDir(mainClassPath);
        try {
            FreemarkerUtil.createFile(templatePath, "Main.java.ftl", mainClassPath, projectName + ".java", paramMap);
        } catch (Exception e) {
            log.error(e.toString());
            throw new AlgException("");
        }
    }
    @Override
    public boolean moduleAntBuild(String  path)throws AlgException,Exception{
        File buildFile = new File(path);
        Project project = new Project();
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
        project.addBuildListener(consoleLogger);

        try {
            project.fireBuildStarted();  //项目开始构建
            project.init();
            ProjectHelper helper = ProjectHelper.getProjectHelper();
            helper.parse(project, buildFile);
            project.executeTarget(project.getDefaultTarget());
            project.fireBuildFinished(null);  //构建结束

        //    moduleAntZip("E:\\repo\\qihe\\TestJava2\\");
        } catch (BuildException e) {
            log.info("构建错误",e);
            project.fireBuildFinished(e);  //构建抛出异常
            throw new Exception(e);
          //  return false;
        }
        return true;

    }


}
