package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.AlgCatRankService;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgModuleUsageMapper;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgModuleUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 1:16
 */
@Service
public class AlgCatRankServiceImpl implements AlgCatRankService {
    @Autowired
    AlgModuleMapper algModuleMapper;
    @Autowired
    AlgModuleUsageMapper algModuleUsageMapper;

    @Override
    public List<AlgModule> listAlg(String catName, String usage) {
        //初步设定用数据库进行排序查询
        //List<AlgModuleUsage> resultUsage = algModuleUsageMapper.listAlgByUsage(usage);
        List<AlgModule> resultAlgModule = algModuleMapper.listAlgByUsage(catName,usage);
        return resultAlgModule;
    }
}
