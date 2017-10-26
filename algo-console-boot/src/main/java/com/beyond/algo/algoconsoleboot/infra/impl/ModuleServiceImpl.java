package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.ModuleAdapter.infra.CreateModuleAdapter;
import com.beyond.algo.algoconsoleboot.infra.ModuleService;
import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigModel;
import com.beyond.algo.common.Assert;
import com.beyond.algo.common.FileNodes;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgProgramLangMapper;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgProgramLang;
import com.beyond.algo.vo.AlgModuleEditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.beyond.algo.common.StringConstant.src;

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
        String ss = "Java";

        AlgModule algModule = findByUsrSnAndModId(username,projectName);
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(algModule.getLanSn());
        //适配器模式 调用创建算法项目适配器
        CreateModuleAdapter createModuleAdapter = (CreateModuleAdapter)Class.forName("com.beyond.algo.algoconsoleboot.ModuleAdapter.Create"+ algProgramLang.getLanName() +"Module").newInstance();
        createModuleAdapter.createModule(username,projectName,gitConfigModel,projectConfigModel);

    }

    //返回文件的后缀名
    @Override
    public String getModuleMainFilePath(String usrCode,String modId,String lanSn) throws Exception {
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(lanSn);
        //TODO 项目名称初始化Tree
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
