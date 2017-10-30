package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/10/28 19:49
 */
public interface AntApiService {

    boolean moduleAntBuild(AlgUser algUser,String modId) throws AlgException;
}
