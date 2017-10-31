package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.adapter.infra.ModuleAdapter;
import com.beyond.algo.algoconsoleboot.infra.AntApiService;
import com.beyond.algo.algoconsoleboot.infra.JGitService;
import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.GitUser;
import com.beyond.algo.common.AdapterUtil;
import com.beyond.algo.constant.Constant;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgProgramLangMapper;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgProgramLang;
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
    @Autowired
    private AlgModuleMapper algModuleMapper;
    @Autowired
    private AlgProgramLangMapper algProgramLangMapper;
    @Autowired
    private JGitService jGitService;

    public boolean moduleAntBuild(GitUser gitUser) throws AlgException ,Exception {
        AlgModule algModule = findByUsrSnAndModId(gitUser.getUsrSn(),gitUser.getModId());
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(algModule.getLanSn());
        //适配器模式 调用创建算法项目适配器
      //  ModuleAdapter javaModuleAdapter = (ModuleAdapter)Class.forName("com.beyond.algo.algoconsoleboot.adapter."+ algProgramLang.getLanName() +"ModuleAdapter").newInstance();
        ModuleAdapter javaModuleAdapter =(ModuleAdapter) AdapterUtil.moduleAdapter(algProgramLang.getLanName());
     //   createModuleAdapter.createModule(algUser.getUsrCode(),projectName,gitConfigModel,projectConfigModel);
        String path=gitConfigModel.getLocalBasePath()+File.separator+gitUser.getUsrCode()+File.separator+gitUser.getModId()+File.separator+ Constant.map.get(algProgramLang.getLanName());
        gitUser.setPath(gitConfigModel.getLocalBasePath()+File.separator+gitUser.getUsrCode()+File.separator+gitUser.getModId()+File.separator+".git");
        log.info("项目编译路径:"+path);
        boolean gitResult=jGitService.initCommitAndPushAllFiles(gitUser);
        boolean buildResult= javaModuleAdapter.moduleAntBuild(path);
        if(buildResult){
            String projectPath=gitConfigModel.getLocalBasePath()+File.separator+gitUser.getUsrCode()+File.separator+gitUser.getModId();
            return buildResult;
          //  moduleAntClassJar(String projectPath)
        }
        return false;
}
    public AlgModule findByUsrSnAndModId(String usrSn, String modId) throws AlgException{
        return algModuleMapper.selectByUsrSnAndModId(usrSn,modId);
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
