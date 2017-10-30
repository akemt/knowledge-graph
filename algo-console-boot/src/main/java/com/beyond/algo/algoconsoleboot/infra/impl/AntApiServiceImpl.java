package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.AntApiService;
import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.GitUser;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.taskdefs.Ant;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/10/28 19:49
 */
@Service
@Slf4j
public class AntApiServiceImpl implements AntApiService {
    @Autowired
    private GitConfigModel gitConfigModel;


    public boolean moduleAntBuild(GitUser gitUser) throws AlgException {
        String path=gitConfigModel.getLocalBasePath()+File.separator+gitUser.getUsrCode()+File.separator+gitUser.getModId()+File.separator+"build.xml";
        log.info("项目编译路径"+path);
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

     //       moduleAntZip("E:\\repo\\qihe\\TestJava2\\");
        } catch (BuildException e) {
            project.fireBuildFinished(e);  //构建抛出异常
            return false;
        }
            return true;
}

    public boolean moduleAntZip(String path)  throws  AlgException{
        Project project = new Project();
        Zip zip = new Zip();
        zip.setProject(project);
        zip.setDestFile(new File("E:\\repo\\qihe\\TestJava2\\beyondalgo.zip"));
        FileSet fileSet = new FileSet();
        fileSet.setProject(project);
        fileSet.setDir(new File(path));
        zip.addFileset(fileSet);
        zip.execute();
        return true;
    }

    public boolean moduleAntClassJar(String path){
        Project project=new Project();
        Jar jar=new Jar();
        jar.setProject(project);
        jar.setDestFile(new File("E:\\repo\\qihe\\TestJava2"));
        FileSet fileSet=new FileSet();
        fileSet.setProject(project);
        fileSet.setDir(new File("d:javaprjprj1bin"));
        fileSet.setIncludes("**/*.class,**/*.properties");
        jar.addFileset(fileSet);
        jar.execute();

        return true;
    }
}
