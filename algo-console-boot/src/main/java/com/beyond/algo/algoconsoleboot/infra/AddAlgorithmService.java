package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.vo.AddAlgorithmVo;

public interface AddAlgorithmService {

    /**
     * lindewei
     * 新增算法
     * @param addAlgorithmVo 新增算法信息
     */
    Boolean addAlgorithm(AddAlgorithmVo addAlgorithmVo) throws AlgException;
}
