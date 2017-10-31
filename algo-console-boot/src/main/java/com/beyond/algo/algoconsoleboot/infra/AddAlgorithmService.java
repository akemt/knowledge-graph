package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgDic;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgUser;

public interface AddAlgorithmService {

    /**
     * lindewei
     * 新增算法
     * @param usrSn,lanName,catName,licName,modName,ModId,needWeb,needCallOther,envType 新增算法信息
     */
    Boolean addAlgorithm(String usrSn,String lanName,String catName,String licName,String modName,String ModId,
                         String needWeb,String needCallOther,String envType) throws AlgException;
}
