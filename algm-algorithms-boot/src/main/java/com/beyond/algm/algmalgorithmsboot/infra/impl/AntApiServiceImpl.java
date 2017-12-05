package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.adapter.infra.ModuleAdapter;
import com.beyond.algm.algmalgorithmsboot.infra.AntApiService;
import com.beyond.algm.algmalgorithmsboot.infra.JGitService;
import com.beyond.algm.algmalgorithmsboot.infra.PathService;
import com.beyond.algm.algmalgorithmsboot.model.GitConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.common.AdapterUtil;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.FileUtil;
import com.beyond.algm.constant.Constant;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgModuleMapper;
import com.beyond.algm.mapper.AlgModuleVersionMapper;
import com.beyond.algm.mapper.AlgProgramLangMapper;
import com.beyond.algm.model.AlgModule;
import com.beyond.algm.model.AlgModuleVersion;
import com.beyond.algm.model.AlgProgramLang;
import lombok.extern.slf4j.Slf4j;
import org.apache.ivy.ant.IvyRetrieve;
import org.apache.tools.ant.*;
import org.apache.tools.ant.taskdefs.Jar;
import org.apache.tools.ant.taskdefs.Manifest;
import org.apache.tools.ant.taskdefs.ManifestException;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

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
    @Autowired
    private PathService pathService;

    public void moduleAntBuild(GitUser gitUser) throws AlgException {
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
        String path=pathService.getModuleBasePath(gitUser.getUsrCode(),gitUser.getModId())+File.separator+ Constant.map.get(algProgramLang.getLanName());
        gitUser.setPath(pathService.getModuleBasePath(gitUser.getUsrCode(),gitUser.getModId())+File.separator+".git");
        log.debug("项目编译路径:{},上传git路径:{}",path,gitUser.getPath());
        String version=jGitService.commitAndPushAllFiles(gitUser);
        AlgModuleVersion algModuleVersion=new AlgModuleVersion();
        algModuleVersion.setLatestCommit(version);
        algModuleVersion.setModSn(algModule.getModSn());
        algModuleVersionMapper.updateLatestCommit(algModuleVersion);
        moduleAdapter.moduleAntBuild(path);
    }
    public AlgModule findByUsrSnAndModId(String usrSn, String modId) throws AlgException{
        return algModuleMapper.selectByUsrSnAndModId(usrSn,modId);
    }

    public void moduleAntZip(String usrCode,String modId) throws AlgException{
        File buildFile = new File("E:/repo/erniu4/TestJavaK1/build.xml");
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
            Target target = new Target();
            target.setProject(project);

            target.setName("publishzip");
            target.setDepends("compile");
            target.setDescription("publish module ant zip");

            Jar jar = new Jar();
            /*FileUtil.createDir("E:/repo/erniu4/TestJavaK1/dist");
            File jarFile = new File("E:/repo/erniu4/TestJavaK1/dist/"+usrCode+"-"+modId+".jar");
            if(!jarFile.exists()){
                jarFile.createNewFile();
            }*/
            jar.setDestFile(new File("E:/repo/erniu4/TestJavaK1/dist/"+usrCode+"-"+modId+".jar"));
            jar.setBasedir(new File("E:/repo/erniu4/TestJavaK1/bin"));

            Manifest manifest = new Manifest();
            Manifest.Attribute attribute = new Manifest.Attribute();
            attribute.setName("Main-Class");
            attribute.setValue("algmarket."+ modId +"."+modId);
            manifest.addConfiguredAttribute(attribute);
            jar.addConfiguredManifest(manifest);
            target.addTask(jar);

            IvyRetrieve ivyRetrieve = new IvyRetrieve();
            ivyRetrieve.setPattern("E:/repo/erniu4/TestJavaK1/dist/[organisation]/[artifact]/[artifact]-[revision](-[classifier]).[ext]");
            ivyRetrieve.setType("jar,bundle");
            target.addTask(ivyRetrieve);
            /*Location location = new Location("E:/repo/erniu4/TestJavaK1/build.xml");
            target.setLocation(location);*/
            project.addTarget(target);
            project.init();
            helper.parse(project, buildFile);
            project.executeTarget("publishzip");
            project.fireBuildFinished(null);  //构建结束

            //    moduleAntZip("E:\\repo\\qihe\\TestJava2\\");
        } catch (BuildException e) {
            log.error("发布错误", e);
            project.fireBuildFinished(e);  //构建抛出异常
            throw new AlgException(e);
        } catch (ManifestException e) {
            log.error("发布添加Manifest出错", e);
            project.fireBuildFinished(e);  //构建抛出异常
        }
    }
}
