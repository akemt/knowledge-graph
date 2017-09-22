package com.beyond.algo.infra.impl;

import com.beyond.algo.common.FileUntil;
import com.beyond.algo.common.ZipUtil;
import com.beyond.algo.infra.BuildAntProjectService;
import com.beyond.algo.infra.JGitService;
import com.beyond.algo.model.GitUser;
import com.beyond.algo.model.ProjectConfigModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
public class BuildAntProjectServiceImpl implements BuildAntProjectService {
    @Autowired
    private JGitService jGitService;
    @Autowired
    private ProjectConfigModel projectConfigModel;


    private final static Logger logger = LoggerFactory.getLogger(BuildAntProjectServiceImpl.class);
    /**
     * ant项目进行编译打包同时解压到指定目录并且代码上传git上
     *
     * @param gitUser
     */
    @Override
    public void buildAndUpLoadProject(GitUser gitUser) throws Exception {
        // 运行window下ant构建指令
        Process processAnt = Runtime.getRuntime().exec("cmd /c ant",null,new File(gitUser.getPath()));
        Process processBuild= Runtime.getRuntime().exec("cmd /c build.sh",null,new File(gitUser.getPath()));
        int antCount=processAnt.waitFor();
        int buildCount=processBuild.waitFor();
        // 如果构建打包没有问题
    //    if(antCount>0&&buildCount>0){
         if( FileUntil.searchFile(gitUser.getPath()))  {
             String localPath=gitUser.getPath()+File.separator +projectConfigModel.getPackageName()+".zip";
             logger.info("zip包路径:"+localPath);
             ZipUtil.unZip(localPath,gitUser.getDescDir());
             jGitService.initCommitAndPushAllFiles(gitUser.getPath()+File.separator+".git",gitUser.getUsername(),gitUser.getPassword());
          // jGitService.initCommitAndPushAllFiles(gitUser.getPath()+"/.git",gitUser.getUsername(),gitUser.getPassword());
         }else{
             logger.info("没有zip");
         }
    //    }
    }

}
