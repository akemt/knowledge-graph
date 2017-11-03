package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.vo.AlgModuleVo;

/**
 * @author ：zhangchuanzhi
 * @Description:算法描述
 * @date ：13:29 2017/10/24
 */
public interface AlgorithmDetailService {
    AlgModuleVo getAlgorithmDetail(String modSn) throws AlgException;
}
