package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.adapter.infra.PublishAdapter;
import com.beyond.algm.algmalgorithmsboot.infra.*;
import com.beyond.algm.algmalgorithmsboot.model.PublishConfigModel;
import com.beyond.algm.common.AdapterUtil;
import com.beyond.algm.constant.Constant;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgModuleMapper;
import com.beyond.algm.mapper.AlgProgramLangMapper;
import com.beyond.algm.mapper.AlgUserMapper;
import com.beyond.algm.model.AlgModule;
import com.beyond.algm.model.AlgModuleVersion;
import com.beyond.algm.model.AlgProgramLang;
import com.beyond.algm.model.AlgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private AlgProgramLangMapper algProgramLangMapper;
    @Autowired
    private PublishConfigModel publishConfigModel;
    @Autowired
    private DockerService dockerService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private AlgUserMapper algUserMapper;
    @Autowired
    private AlgModuleMapper algModuleMapper;
    @Autowired
    private PathService pathService;
    @Autowired
    private MvnService mvnService;
    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void initBootProject(String lanName, String usrCode, String modId, String modDesc, String version) throws AlgException {

        PublishAdapter publishAdapter =(PublishAdapter) AdapterUtil.publishAdapter(lanName);
        String modPath = pathService.getModuleBasePath(usrCode,modId);
        String publishPath = pathService.getPublishPath(usrCode, modId);
        publishAdapter.initBootProject(usrCode, modId, modDesc, version, publishConfigModel, active,modPath,publishPath);
    }



    @Transactional(rollbackFor = AlgException.class)
    @Override
    public void publishModule(String modId,String usrCode,String verMark)throws AlgException{
        AlgUser algUser = algUserMapper.selectUsrCode(usrCode);
        AlgModule algModule = algModuleMapper.selectByUsrSnAndModId(algUser.getUsrSn(),modId);
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(algModule.getLanSn());
        //版本保存到数据库
        AlgModuleVersion algModuleVersion = moduleService.addVersion(usrCode, modId, verMark);
        String version = getVersionStr(algModuleVersion);
        //创建 发布包
        initBootProject(algProgramLang.getLanName(),usrCode,modId,algModule.getModDesc(),version);

        //TODO：调用mvnService编译工程
        mvnService.mvnPackageMod(usrCode,modId);
        // 制作Dockerfile
        String publishPath = pathService.getPublishPath(usrCode, modId);
        String jarFileName = usrCode + "-" + modId + "-" + version + Constant.JAR_SUFFIX;
        dockerService.makeDockerfile(active,algProgramLang.getLanName(),publishPath + File.separator + Constant.TARGET,jarFileName);
        //调用dockerApi封装docker镜像
        dockerService.bulidDockerImage(modId,usrCode,version,publishPath + File.separator + Constant.TARGET);

        //TODO：推送到harbor上

        //TODO：启动k8s
    }

    private String getVersionStr(AlgModuleVersion algModuleVersion){
        String version = algModuleVersion.getVerCodeL1()+"."+algModuleVersion.getVerCodeL2()+"."+algModuleVersion.getVerCodeL3();
        return version;
    }
}
