package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.ModuleService;
import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigModel;
import com.beyond.algo.common.Assert;
import com.beyond.algo.common.FileNodes;
import com.beyond.algo.common.FileUtil;
import com.beyond.algo.algoconsoleboot.util.FreemarkerUtil;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgProgramLangMapper;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgProgramLang;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AlgModuleEditVo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.beyond.algo.common.StringConstant.*;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private ProjectConfigModel projectConfigModel;
    @Autowired
    private AlgModuleMapper algModuleMapper;
    @Autowired
    private AlgProgramLangMapper algProgramLangMapper;
    @Autowired
    private ShowProjectFileService showProjectFileService;

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
        String templatePath = new ClassPathResource("templates/project/java").getFile().getPath();
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

    //返回文件的后缀名
    @Override
    public String getModuleMainFilePath(String usrCode,String modId,String lanSn) throws Exception {
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(lanSn);
        //项目名称初始化Tree
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gitConfigModel.getLocalBasePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(usrCode);
        stringBuilder.append(File.separator);
        stringBuilder.append(modId);
        stringBuilder.append(File.separator);
        stringBuilder.append(src);
        stringBuilder.append(File.separator);
        stringBuilder.append(projectConfigModel.getPackageName());
        stringBuilder.append(File.separator);
        stringBuilder.append(modId);
        stringBuilder.append(File.separator);

        return stringBuilder.toString();
    }

    public AlgModule findByUsrSnAndModId(String usrSn,String modId) throws Exception{
        return algModuleMapper.selectByUsrSnAndModId(usrSn,modId);
    }

    @Override
    public AlgModuleEditVo AlgModule(String usrCode,String usrSn,String modId,String path) throws Exception{
        AlgModuleEditVo algModuleEditVo = new AlgModuleEditVo();
        //log.info("current user:{} ",algUser.getUsrCode());
        AlgModule algModule = this.findByUsrSnAndModId(usrSn,modId);
        //log.info("current project id:{} ,name :{} ",algModule.getModId(),algModule.getModName());
        //项目名称初始化Tree
        if (Assert.isEmpty(path)){
            path = this.getModuleMainFilePath(usrCode,modId,algModule.getLanSn());
        }else{
            path = showProjectFileService.getSplitPath(usrCode,modId)+"/"+path;
        }
        //log.info("current path {} ",path);
        //返回同级目录所有文件和文件夹.
        FileNodes fileNodes = showProjectFileService.ShowProjectFile(path,usrCode,modId);
        //log.info("current fileNodes {} ",fileNodes.toString());
        algModuleEditVo.setModId(algModule.getModId());
        algModuleEditVo.setModName(algModule.getModName());
        algModuleEditVo.setLatestCommit("b975b7748b90f259240156c6e39129f058ebb141");
        algModuleEditVo.setLatestVersion("0.0.3");
        algModuleEditVo.setFileNodes(fileNodes);
        return algModuleEditVo;
    }
}
