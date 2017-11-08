package com.beyond.algo.algoalgorithmsboot.infra.impl;

import com.beyond.algo.algoalgorithmsboot.infra.AlgModuleListService;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgModuleUsageMapper;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.vo.AlgModuleListVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 1:16
 */
@Service
public class AlgModuleListServiceImpl implements AlgModuleListService {
    @Autowired
    AlgModuleMapper algModuleMapper;
    @Autowired
    AlgModuleUsageMapper algModuleUsageMapper;

    @Override
    public List<AlgModuleListVo> findModuleList(String catName, String usage ,String modName,Integer numPage,Integer numRows) throws AlgException {
        //初步设定用数据库进行排序查询
        PageHelper.startPage(numPage,numRows);
        List<AlgModuleListVo> resultAlgModule = algModuleMapper.findModuleList(catName,usage,modName);
        return resultAlgModule;
    }
}