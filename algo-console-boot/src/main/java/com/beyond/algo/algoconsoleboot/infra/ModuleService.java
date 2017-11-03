package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgModuleVersion;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AlgModuleEditVo;

public interface ModuleService {

    /**
     * 初始化算法工程.
     */
    void initProject(AlgUser algUser, String projectName) throws Exception;

    AlgModule findByUsrSnAndModId(String usrSn,String modId) throws AlgException;

    String getModuleMainFilePath(String usrCode,String modId,String lanSn) throws AlgException;

    /**
     *
     * @param usrCode
     * @param usrSn
     * @param modId
     * @param path
     * @return
     * @throws AlgException
     */
    AlgModuleEditVo algModule(String usrCode,String usrSn,String modId,String path) throws AlgException;

    AlgModuleVersion getLastVersion(String mod_sn) throws AlgException;

    /**
     * lindewei
     * 新增算法
     * @param algModule 新增算法信息
     */
    Boolean addAlgModule(AlgModule algModule,AlgUser algUser) throws AlgException;
}