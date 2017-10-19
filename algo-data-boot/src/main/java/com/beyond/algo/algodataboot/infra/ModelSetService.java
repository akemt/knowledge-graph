package com.beyond.algo.algodataboot.infra;

import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgModelSet;


/**
 * @author huangjinqing
 * @Description:算法商店模型模块service接口声明
 * @version Created in：20:22 2017/10/17
 */

public interface ModelSetService {

    /**
     * @author ：huangjinqing
     * @Description:添加模型集
     * @param：User
     * @date ：20:22 2017/10/17
     */
    Result addModelSet(AlgModelSet modelSet) throws Exception;
}
