package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.AddAlgorithmService;
import com.beyond.algo.common.Assert;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgAlgoCategoryMapper;
import com.beyond.algo.mapper.AlgLicenseMapper;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgProgramLangMapper;
import com.beyond.algo.model.AlgAlgoCategory;
import com.beyond.algo.model.AlgLicense;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgProgramLang;
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
                                String isOpenSrc,String needWeb,String needCallOther,String envType,String isTrain,
                                String isColony,String colonyPlanId) throws AlgException{
        //用户串号
        algModule.setUsrSn(usrSn);
        //语言串号
        AlgProgramLang algProgramLang = algProgramLangMapper.selectLanSn(lanName);
        if( Assert.isEmpty(algProgramLang)){
            String[] checkMessage = {"语言串号",""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000001",checkMessage);
        }
        algModule.setLanSn(algProgramLang.getLanSn());
        //分类串号
        AlgAlgoCategory algAlgoCategory = algAlgoCategoryMapper.selectCatSn(catName);
        if( Assert.isEmpty(algAlgoCategory)){
            String[] checkMessage = {"分类串号",""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000001",checkMessage);
        }
        algModule.setCatSn(algAlgoCategory.getCatSn());
        //协议串号
        AlgLicense algLicense = algLicenseMapper.selectLicSn(licName);
        if( Assert.isEmpty(algLicense)){
            String[] checkMessage = {"协议串号",""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000001",checkMessage);
        }
        algModule.setLicSn(algLicense.getLicSn());
        //文章串号
        //algModule.setAtlSn(null);
        //模块名
        algModule.setModName(modName);
        //模块ID
        algModule.setModId(ModId);
        //是否开源:开源；不开源
        algModule.setIsOpenSrc(isOpenSrc);
        //是否要存取网络:需要；不需要
        algModule.setNeedWeb(needWeb);
        //是否要调用其他算法(开源:Algmarket platform Licese、apache协议....;不开源：Algmarket platform Licese)
        algModule.setNeedCallOther(needCallOther);
        //运行环境:GPU;CPU
        algModule.setEnvType(envType);
        //是否训练接口
        algModule.setIsTrain(isTrain);
        //是否集群
        algModule.setIsColony(isColony);
        //集群方案ID
        algModule.setColonyPlanId(colonyPlanId);
        // 新增算法
        try {
            algModuleMapper.insert(algModule);
        } catch (Exception e) {
            throw new AlgException("新增算法插入失败，用户串号：" + usrSn + "，语言串号：" + algProgramLang.getLanSn()
                    + "，分类串号：" + algAlgoCategory.getCatSn() + "，语言串号：" + algProgramLang.getLanSn() + "。", e);
        }
        return false;
    }
}
