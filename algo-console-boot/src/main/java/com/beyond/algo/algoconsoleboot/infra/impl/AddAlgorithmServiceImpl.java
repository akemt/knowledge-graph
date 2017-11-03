package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.AddAlgorithmService;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgModuleVersionMapper;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgModuleVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AddAlgorithmServiceImpl implements AddAlgorithmService {

    @Autowired
    private AlgModuleMapper algModuleMapper;
    @Autowired
    private AlgModuleVersionMapper algModuleVersionMapper;
    /**
     * @author ：lindw
     * @Description:算法新增
     */
    @Override
    @Transactional
    public Boolean addAlgModule(AlgModule algModule) throws AlgException{
        //模块串号
        algModule.setModSn(UUIDUtil.createUUID());
        // 新增算法
        try {
            algModuleMapper.insert(algModule);
            AlgModuleVersion algModuleVersion = new AlgModuleVersion();
            algModuleVersion.setVerSn(UUIDUtil.createUUID());
            algModuleVersion.setCreateDate(new Date());
            algModuleVersion.setVerCodeL1(0);
            algModuleVersion.setVerCodeL2(0);
            algModuleVersion.setVerCodeL3(1);
            //TODO 未实现版本 等其他信息
            algModuleVersion.setLatestCommit("1234567890123456789");
            algModuleVersion.setModSn(algModule.getModSn());
            algModuleVersionMapper.insert(algModuleVersion);
        } catch (Exception e) {
            throw new AlgException("新增算法插入失败，用户串号：" + algModule.getUsrSn() + "，语言串号：" + algModule.getLanSn()
                    + "，分类串号：" + algModule.getCatSn() + "，语言串号：" + algModule.getLanSn() + "。", e);
        }
        return false;
    }
}
