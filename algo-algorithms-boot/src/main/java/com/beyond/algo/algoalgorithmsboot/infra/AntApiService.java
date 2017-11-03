package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.algoalgorithmsboot.model.GitUser;
import com.beyond.algo.exception.AlgException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/10/28 19:49
 */
public interface AntApiService {

    void moduleAntBuild(GitUser gitUser) throws AlgException;
}
