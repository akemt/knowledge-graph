package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/23 11:03
 */
public interface MvnService {
    /**
     *发布MVN 项目
     *
     * @param userCode
     * @param modId
     * @param curUsrCode
     * @param isOrg
     * @throws AlgException
     */
    void mvnPackageMod(String userCode, String modId,String curUsrCode,String isOrg) throws AlgException;

}
