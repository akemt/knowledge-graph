package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.vo.AlgModuleVo;

import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:算法描述
 * @date ：13:29 2017/10/24
 */
public interface AlgorithmDetailService {
    AlgModuleVo getAlgorithmDetail(String modSn);
}
