package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.exception.AlgException;

public interface AddAlgorithmService {

    /**
     * lindewei
     * 新增算法
     * @param usrSn,lanName,catName,licName,modName,ModId,needWeb,needCallOther,envType 新增算法信息
     */
    Boolean addAlgorithm(String usrSn,String lanName,String catName,String licName,String modName,String ModId,
                         String isOpenSrc,String needWeb,String needCallOther,String envType,String isTrain,
                         String isColony,String colonyPlanId) throws AlgException;
}
