package com.beyond.algo.algodataboot.infra.impl;

import com.beyond.algo.algodataboot.infra.ModelSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgModelSet;
import com.beyond.algo.mapper.AlgModelSetMapper;

import java.util.UUID;

/**
 * @author huangjinqing
 * @version Created in：20:22 2017/10/17
 * @Description:算法商店模型模块service接口实现
 */

@Service
public class ModelSetServiceImpl implements ModelSetService {

    @Autowired
    private AlgModelSetMapper algModelSetMapper;

    @Override
    public Result addModelSet(AlgModelSet modelSet) throws Exception {
        try {
            //生成模型集随机串
            if(modelSet.getModelSetName().isEmpty()){
                return Result.failure("模型集名称为空");
            }
           modelSet.setModelSetSn(UUID.randomUUID().toString().replace("-", ""));
            algModelSetMapper.insert(modelSet);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failureResponse();
        }
        return Result.successResponse();
    }

}
