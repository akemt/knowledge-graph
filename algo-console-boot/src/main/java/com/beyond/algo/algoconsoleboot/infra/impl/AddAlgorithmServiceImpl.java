package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.AddAlgorithmService;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgProgramLangMapper;
import com.beyond.algo.mapper.AlgAlgoCategoryMapper;
import com.beyond.algo.mapper.AlgLicenseMapper;
import com.beyond.algo.model.AlgDic;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgProgramLang;
import com.beyond.algo.model.AlgAlgoCategory;
import com.beyond.algo.model.AlgLicense;
import org.springframework.beans.factory.annotation.Autowired;

public class AddAlgorithmServiceImpl implements AddAlgorithmService {

    @Autowired
    private AlgModule algModule;
    @Autowired
    private AlgModuleMapper algModuleMapper;
    @Autowired
    private AlgProgramLangMapper algProgramLangMapper;
    @Autowired
    private AlgAlgoCategoryMapper algAlgoCategoryMapper;
    @Autowired
    private AlgLicenseMapper algLicenseMapper;

    public Boolean addAlgorithm(String usrSn,String lanName,String catName,String licName,String modName,String ModId,
                                String needWeb,String needCallOther,String envType) throws AlgException{
        algModule.setUsrSn(usrSn);

        AlgProgramLang algProgramLang = algProgramLangMapper.selectLanSn(lanName);
        algModule.setLanSn(algProgramLang.getLanSn());

        AlgAlgoCategory algAlgoCategory = algAlgoCategoryMapper.selectCatSn(catName);
        algModule.setCatSn(algAlgoCategory.getCatSn());

        AlgLicense algLicense = algLicenseMapper.selectLicSn(licName);
        algModule.setLicSn(algLicense.getLicSn());

        //todo 文章串号
        algModule.setAtlSn(null);

        algModule.setModName(modName);
        algModule.setModId(ModId);

        //todo 是否开源
        algModule.setIsOpenSrc(algLicense.getLicContent());
        //todo 是否要存取网络
        algModule.setNeedWeb(needWeb);
        //todo 是否要调用其他算法
        algModule.setNeedCallOther(needCallOther);
        //todo 运算环境类型
        algModule.setEnvType(envType);

        // 新增算法
        try {
            algModuleMapper.insert(algModule);
        } catch (Exception e) {
            throw new AlgException("新增算法插入失败，创建者code：" + null + "，组织code：" + null + "。", e);//todo
        }
        return false;
    }
}
