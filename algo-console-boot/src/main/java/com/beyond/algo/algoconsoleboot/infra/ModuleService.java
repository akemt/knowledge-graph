package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.model.AlgModule;
import com.beyond.algo.vo.AlgModuleEditVo;

public interface ModuleService {

    /**
     * 初始化算法工程.
     */
    void initProject(String username, String projectName) throws Exception;

    AlgModule findByUsrSnAndModId(String usrSn,String modId) throws Exception;

    String getModuleMainFilePath(String usrCode,String modId,String lanSn) throws Exception;

    AlgModuleEditVo algModule(String usrCode,String usrSn,String modId,String path) throws Exception;
}
