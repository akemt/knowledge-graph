package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.AlgorithmDetailService;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.vo.AlgModuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public AlgModuleVo getAlgorithmDetail(String modSn)throws AlgException {
        AlgModuleVo algModuleVo=algModuleMapper.getAlgorithmDetail(modSn);
        return algModuleVo;
    }
}
