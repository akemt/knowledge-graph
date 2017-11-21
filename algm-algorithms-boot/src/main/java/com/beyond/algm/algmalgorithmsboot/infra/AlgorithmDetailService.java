package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.vo.AlgModuleVo;
import com.beyond.algm.vo.AlgorithmDetailVo;

/**
 * @author ：zhangchuanzhi
 * @Description:算法描述
 * @date ：13:29 2017/10/24
 */
public interface AlgorithmDetailService {
    AlgModuleVo getAlgorithmDetail(AlgorithmDetailVo algorithmDetailVo) throws AlgException;
}
