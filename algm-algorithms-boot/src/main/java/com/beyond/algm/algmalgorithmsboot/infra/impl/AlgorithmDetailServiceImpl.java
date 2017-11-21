package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.AlgorithmDetailService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgModuleMapper;
import com.beyond.algm.vo.AlgModuleVo;
import com.beyond.algm.vo.AlgorithmDetailVo;
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
