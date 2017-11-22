package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.adapter.infra.ModuleAdapter;
import com.beyond.algm.algmalgorithmsboot.infra.AntApiService;
import com.beyond.algm.algmalgorithmsboot.infra.JGitService;
import com.beyond.algm.algmalgorithmsboot.model.GitConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.common.AdapterUtil;
import com.beyond.algm.common.Assert;
import com.beyond.algm.constant.Constant;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgModuleMapper;
import com.beyond.algm.mapper.AlgModuleVersionMapper;
import com.beyond.algm.mapper.AlgProgramLangMapper;
import com.beyond.algm.model.AlgModule;
import com.beyond.algm.model.AlgModuleVersion;
import com.beyond.algm.model.AlgProgramLang;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.ant.Project;
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
    @Autowired
    private AlgModuleVersionMapper algModuleVersionMapper;

    public void moduleAntBuild(GitUser gitUser) throws AlgException,Exception {
        AlgModule algModule = findByUsrSnAndModId(gitUser.getUsrSn(),gitUser.getModId());
        if(Assert.isNULL(algModule)){
            String[] checkMessage = {"算法模块",""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000006",checkMessage);
        }
        log.debug("algModule模块的语言串号:{}",algModule.getLanSn());
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(algModule.getLanSn());
        if(Assert.isNULL(algProgramLang)){
            String[] checkMessage = {"语言模块",""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000006",checkMessage);
        }
        log.debug("语言表获得语言:{}",algProgramLang.getLanName());
        //适配器模式 调用创建算法项目适配器
        ModuleAdapter moduleAdapter =(ModuleAdapter) AdapterUtil.moduleAdapter(algProgramLang.getLanName());
        String path=gitConfigModel.getLocalBasePath()+File.separator+gitUser.getUsrCode()+File.separator+gitUser.getModId()+File.separator+ Constant.map.get(algProgramLang.getLanName());
        gitUser.setPath(gitConfigModel.getLocalBasePath()+File.separator+gitUser.getUsrCode()+File.separator+gitUser.getModId()+File.separator+".git");
        log.debug("项目编译路径:{},上传git路径:{}",path,gitUser.getPath());
        String version=jGitService.commitAndPushAllFiles(gitUser);
        AlgModuleVersion algModuleVersion=new AlgModuleVersion();
        algModuleVersion.setLatestCommit(version);
        algModuleVersion.setModSn(algModule.getModSn());
        algModuleVersionMapper.updateLatestCommit(algModuleVersion);
        moduleAdapter.moduleAntBuild(path);
        //String projectPath=gitConfigModel.getLocalBasePath()+File.separator+gitUser.getUsrCode()+File.separator+gitUser.getModId();
        //moduleAntClassJar(String projectPath)
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
