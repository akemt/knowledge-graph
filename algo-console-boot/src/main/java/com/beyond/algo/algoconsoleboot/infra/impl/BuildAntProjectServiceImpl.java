package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.common.FileUtil;
import com.beyond.algo.common.ZipUtil;
import com.beyond.algo.algoconsoleboot.infra.BuildAntProjectService;
import com.beyond.algo.algoconsoleboot.infra.JGitService;
import com.beyond.algo.algoconsoleboot.model.GitUser;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
@Slf4j
public class BuildAntProjectServiceImpl implements BuildAntProjectService {
    @Autowired
    private JGitService jGitService;
    @Autowired
    private ProjectConfigModel projectConfigModel;


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
       // Process processAnt = Runtime.getRuntime().exec(cmd); String cmd = "/home/heyutao/workspace/ChunkOperator/sh/cpu.sh"
        int antCount=processAnt.waitFor();
        int buildCount=processBuild.waitFor();
        // 如果构建打包没有问题
    //    if(antCount>0&&buildCount>0){
         if( FileUtil.searchFile(gitUser.getPath()))  {
             String localPath=gitUser.getPath()+File.separator +projectConfigModel.getPackageName()+".zip";
             log.info("zip包路径:"+localPath);
             ZipUtil.unZip(localPath,gitUser.getDescDir());
             gitUser.setPath(gitUser.getPath()+File.separator+".git");
             jGitService.commitAndPushAllFiles(gitUser);
          // jGitService.initCommitAndPushAllFiles(gitUser.getPath()+"/.git",gitUser.getUsername(),gitUser.getPassword());
         }else{
             log.info("没有zip");
         }
    //    }
    }

}
