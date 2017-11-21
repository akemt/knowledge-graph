package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.exception.AlgException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/10/28 19:49
 */
public interface AntApiService {

    void moduleAntBuild(GitUser gitUser) throws AlgException,Exception;
}
