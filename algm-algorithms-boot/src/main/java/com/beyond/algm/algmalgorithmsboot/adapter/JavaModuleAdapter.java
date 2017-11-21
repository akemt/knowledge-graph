package com.beyond.algm.algmalgorithmsboot.adapter;

import com.beyond.algm.algmalgorithmsboot.adapter.infra.ModuleAdapter;
import com.beyond.algm.algmalgorithmsboot.model.GitConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigModel;
import com.beyond.algm.algmalgorithmsboot.util.FreemarkerUtil;
import com.beyond.algm.common.FileUtil;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.taskdefs.Javac;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.beyond.algm.constant.Constant.FILE_READ_SIZE;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in
 */
@Slf4j
public class JavaModuleAdapter implements ModuleAdapter {

    @Override
    public void createModule(String username, String projectName, GitConfigModel gitConfigModel, ProjectConfigModel projectConfigModel) throws AlgException {
        FileUtil.createDir(gitConfigModel.getLocalBasePath());

        // 以用户名作为用户子目录名
        String userPath = gitConfigModel.getLocalBasePath() + File.separator + username;
        FileUtil.createDir(userPath);

        // 以项目名作为项目子目录名
        String projectPath = userPath + File.separator + projectName;
        FileUtil.createDir(projectPath);

        // 先将直接拷贝的文件复制到目录下
        String templatePath = null;
        templatePath = "jar:" + this.getClass().getClassLoader().getResource("/templates/project/java/").getPath();
        String[] cloneFileArr = projectConfigModel.getCloneFiles().split(",");
        for (String cloneFileName : cloneFileArr) {
            OutputStream ous = null;
            InputStream ins = null;
            try {
                log.debug("templatePath is : {} ",templatePath);
                URL url = new URL(templatePath + cloneFileName);
                log.debug("url path : {}",url.getPath());
                ins = url.openStream();
                //URL读取不到以.开头的文件
                if(cloneFileName.equals("gitignore")){
                    cloneFileName = "." + cloneFileName;
                }
                File destFile = new File(projectPath + File.separator + cloneFileName);
                ous = new FileOutputStream(destFile);
                int bytesRead = 0;
                byte[] buffer = new byte[FILE_READ_SIZE];
                while ((bytesRead = ins.read(buffer, 0, FILE_READ_SIZE)) != -1) {
                    ous.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                log.error(e.toString());
                throw new AlgException("BEYOND.ALG.MODULE.COPY.0000003",new String[]{},e);
            }finally {
                try {
                    ous.close();
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                throw new AlgException("BEYOND.ALG.MODULE.GENERATE.0000004", new String[]{}, e);
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
            throw new AlgException("BEYOND.ALG.MODULE.GENERATE.0000005", new String[]{}, e);
        }
    }

    @Override
    public boolean moduleAntBuild(String path) throws AlgException, Exception {
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
            Javac javac = (Javac) project.createTask("javac");
            javac.setProject(project);
            //是否执行外部的javac
            javac.setFork(true);
            ProjectHelper helper = ProjectHelper.getProjectHelper();
            helper.parse(project, buildFile);
            project.executeTarget(project.getDefaultTarget());
            project.fireBuildFinished(null);  //构建结束

            //    moduleAntZip("E:\\repo\\qihe\\TestJava2\\");
        } catch (BuildException e) {
            log.info("构建错误", e);
            project.fireBuildFinished(e);  //构建抛出异常
            throw new Exception(e);
            //  return false;
        }
        return true;

    }


}
