package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.vo.AlgModuleVo;
import com.beyond.algo.vo.AlgorithmDetailVo;

/**
 * @author ：zhangchuanzhi
 * @Description:算法描述
 * @date ：13:29 2017/10/24
 */
public interface AlgorithmDetailService {
    AlgModuleVo getAlgorithmDetail(AlgorithmDetailVo algorithmDetailVo) throws AlgException;
}
