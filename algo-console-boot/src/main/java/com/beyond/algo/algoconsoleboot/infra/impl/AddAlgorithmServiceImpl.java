package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.AddAlgorithmService;
import com.beyond.algo.common.Assert;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.*;
import com.beyond.algo.model.*;
import com.beyond.algo.vo.AddAlgorithmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddAlgorithmServiceImpl implements AddAlgorithmService {

    @Autowired
    private AlgModuleMapper algModuleMapper;
    @Autowired
    private AlgProgramLangMapper algProgramLangMapper;
    @Autowired
    private AlgAlgoCategoryMapper algAlgoCategoryMapper;
    @Autowired
    private AlgLicenseMapper algLicenseMapper;
    @Autowired
    private AlgDicMapper algDicMapper;

    /**
     * @author ：lindw
     * @Description:算法新增
     */
    @Override
    public Boolean addAlgorithm(AddAlgorithmVo addAlgorithmVo) throws AlgException{
        AlgModule algModule = new AlgModule();
        //模块串号
        algModule.setModSn(UUIDUtil.createUUID());
        //用户串号
        algModule.setUsrSn(addAlgorithmVo.getUsrSn());
        //语言串号
        AlgProgramLang algProgramLang = algProgramLangMapper.selectLanSn(addAlgorithmVo.getLanName());
        if( Assert.isEmpty(algProgramLang)){
            String[] checkMessage = {"语言串号",""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000001",checkMessage);
        }
        algModule.setLanSn(algProgramLang.getLanSn());
        //分类串号
        AlgAlgoCategory algAlgoCategory = algAlgoCategoryMapper.selectCatSn(addAlgorithmVo.getCatName());
        if( Assert.isEmpty(algAlgoCategory)){
            String[] checkMessage = {"分类串号",""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000001",checkMessage);
        }
        algModule.setCatSn(algAlgoCategory.getCatSn());
        //协议串号
        AlgLicense algLicense = algLicenseMapper.selectLicSn(addAlgorithmVo.getLicName());
        if( Assert.isEmpty(algLicense)){
            String[] checkMessage = {"协议串号",""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000001",checkMessage);
        }
        algModule.setLicSn(algLicense.getLicSn());
        //文章串号
        //algModule.setAtlSn(null);
        //模块名
        algModule.setModName(addAlgorithmVo.getModName());
        //模块ID
        algModule.setModId(addAlgorithmVo.getModId());
        //是否开源:开源；不开源
        algModule.setIsOpenSrc(addAlgorithmVo.getIsOpenSrc());
        //是否要存取网络:需要；不需要
        algModule.setNeedWeb(addAlgorithmVo.getNeedWeb());
        //是否要调用其他算法(开源:Algmarket platform Licese、apache协议....;不开源：Algmarket platform Licese)
        algModule.setNeedCallOther(addAlgorithmVo.getNeedCallOther());
        //运行环境:GPU;CPU
        algModule.setEnvType(addAlgorithmVo.getEnvType());
        //是否训练接口
        algModule.setIsTrain(addAlgorithmVo.getIsTrain());
        //是否集群
        algModule.setIsColony(addAlgorithmVo.getIsColony());
        //集群方案ID
        AlgDic algDic = algDicMapper.selectKeyAll(addAlgorithmVo.getDicValue());
        if(!Assert.isEmpty(algDic)){
            algModule.setColonyPlanId(algDic.getDicKey());
        }
        //描述
        algModule.setModDesc(addAlgorithmVo.getModDesc());
        // 新增算法
        try {
            algModuleMapper.insert(algModule);
        } catch (Exception e) {
            throw new AlgException("新增算法插入失败，用户串号：" + addAlgorithmVo.getUsrSn() + "，语言串号：" + algProgramLang.getLanSn()
                    + "，分类串号：" + algAlgoCategory.getCatSn() + "，语言串号：" + algProgramLang.getLanSn() + "。", e);
        }
        return false;
    }
}
