package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/10/28 19:49
 */
public interface AntApiService {

    /**
     * 算法编译
     *
     * @param algUser
     * @param usrCode
     * @param modId
     * @throws AlgException
     * @author xialf
     */
    void moduleAntBuild(AlgUser algUser, String usrCode, String modId) throws AlgException;

    void moduleAntZip(String usrCode,String modId) throws AlgException;
}
