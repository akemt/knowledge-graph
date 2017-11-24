package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.adapter.infra.PublishAdapter;
import com.beyond.algm.algmalgorithmsboot.infra.PublishService;
import com.beyond.algm.algmalgorithmsboot.model.PublishConfigModel;
import com.beyond.algm.common.AdapterUtil;
import com.beyond.algm.mapper.AlgProgramLangMapper;
import com.beyond.algm.model.AlgProgramLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublishServiceImpl implements PublishService {

    @Autowired
    private AlgProgramLangMapper algProgramLangMapper;
    @Autowired
    private PublishConfigModel publishConfigModel;
    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public void initBootProject(String lanSn, String userCode, String projectName, String projectDescription, String algoVersion) throws Exception {
        AlgProgramLang algProgramLang = algProgramLangMapper.selectByPrimaryKey(lanSn);
        PublishAdapter publishAdapter =(PublishAdapter) AdapterUtil.publishAdapter(algProgramLang.getLanName());
        publishAdapter.initBootProject(userCode, projectName, projectDescription, algoVersion, publishConfigModel, active);
    }
}
