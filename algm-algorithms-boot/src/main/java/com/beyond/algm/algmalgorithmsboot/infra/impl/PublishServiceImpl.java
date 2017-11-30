package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.adapter.infra.PublishAdapter;
import com.beyond.algm.algmalgorithmsboot.infra.DockerService;
import com.beyond.algm.algmalgorithmsboot.infra.ModuleService;
import com.beyond.algm.algmalgorithmsboot.infra.PathService;
import com.beyond.algm.algmalgorithmsboot.infra.PublishService;
import com.beyond.algm.algmalgorithmsboot.model.PublishConfigModel;
import com.beyond.algm.common.AdapterUtil;
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
    @Value("${spring.profiles.active}")
    private String active;
    @Autowired
    private PathService pathService;

    @Override
    public void initBootProject(String lanSn, String userCode, String modId, String modDesc, String version) throws AlgException {
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(lanSn);
        PublishAdapter publishAdapter =(PublishAdapter) AdapterUtil.publishAdapter(algProgramLang.getLanName());
        String modPath = pathService.getModuleBasePath(userCode,modId);
        publishAdapter.initBootProject(userCode, modId, modDesc, version, publishConfigModel, active,modPath,"");
    }



    @Transactional(rollbackFor = AlgException.class)
    @Override
    public void publishModule(String modId,String usrCode,String verMark)throws AlgException{
        AlgUser algUser = algUserMapper.selectUsrCode(usrCode);
        AlgModule algModule = algModuleMapper.selectByUsrSnAndModId(algUser.getUsrSn(),modId);
        //版本保存到数据库
        AlgModuleVersion algModuleVersion = moduleService.addVersion(usrCode, modId, verMark);
        String version = getVersionStr(algModuleVersion);
        //创建 发布包
        initBootProject(algModule.getLanSn(),usrCode,modId,algModule.getModDesc(),version);
        //调用dockerApi封装docker镜像
        //dockerService.bulidDockerImage(modId,usrCode,version);

        //TODO：并推送到harbor上

        //TODO：启动k8s
    }

    private String getVersionStr(AlgModuleVersion algModuleVersion){
        String version = algModuleVersion.getVerCodeL1()+"."+algModuleVersion.getVerCodeL2()+"."+algModuleVersion.getVerCodeL3();
        return version;
    }
}
