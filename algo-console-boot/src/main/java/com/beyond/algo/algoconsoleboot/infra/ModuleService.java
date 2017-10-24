package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.model.AlgModule;

public interface ModuleService {

    /**
     * 初始化算法工程.
     */
    void initProject(String username, String projectName) throws Exception;

    AlgModule findByUsrSnAndModId(String usrSn,String modId) throws Exception;
}
