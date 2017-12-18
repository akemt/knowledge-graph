package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/23 11:03
 */
public interface MvnService {
    /**
     * 封装服务：mvn 打包 算法
     * @param userCode
     * @param modId
     * @throws AlgException
     */
    void mvnPackageMod(String userCode, String modId) throws AlgException;

}
