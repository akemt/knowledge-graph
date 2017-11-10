package com.beyond.algo.algoalgorithmsboot.infra.impl;

import com.beyond.algo.algoalgorithmsboot.infra.AlgorithmDetailService;
import com.beyond.algo.common.Assert;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.vo.AlgModuleVo;
import com.beyond.algo.vo.AlgorithmDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author ：zhangchuanzhi
 * @Description:算法详情
 * @date ：13:33 2017/10/24
 */
@Service
public class AlgorithmDetailServiceImpl implements AlgorithmDetailService {
    @Autowired
    private AlgModuleMapper algModuleMapper;
    @Override
    public AlgModuleVo getAlgorithmDetail(AlgorithmDetailVo algorithmDetailVo)throws AlgException {
        AlgModuleVo algModuleVo=algModuleMapper.getAlgorithmDetail(algorithmDetailVo);
        if(Assert.isNotNULL(algModuleVo)) {
            algModuleVo.setUrl(algorithmDetailVo.getUsrCode() + File.separator + algorithmDetailVo.getModId() + File.separator + "edit");
            algModuleVo.setDataUrl(algorithmDetailVo.getUsrCode() + File.separator + algorithmDetailVo.getModId());
            algModuleVo.setUsrCode(algorithmDetailVo.getUsrCode());
        }
        return algModuleVo;
    }
}
