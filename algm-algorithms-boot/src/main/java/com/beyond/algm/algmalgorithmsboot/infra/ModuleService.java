package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.*;
import com.beyond.algm.vo.AlgModuleEditVo;

import java.util.List;
import java.util.Map;

public interface ModuleService {

    /**
     * 初始化算法工程.
     */
    void initProject(AlgUser algUser, String projectName,String lanSn) throws Exception;

    AlgModule findByUsrSnAndModId(String usrSn,String modId) throws AlgException;

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

    /**
     * lindewei
     * 新增算法初始化
     */
    Map addInit() throws AlgException;

    /**
     * lindewei
     * 依赖功能：查找语言
     */
    AlgProgramLang getLanguage(String usrCode, String modId) throws AlgException;

    /**
     * lindewei
     * 版本接口
     */
    AlgModuleVersion addVersion(String usrCode,String modId,String verMark)throws AlgException;

    /**
     * lindewei
     * 分类接口
     */
    List<AlgAlgoCategory> category() throws AlgException;

    /**
     * @author ：lindewei
     * @Description: 校验算法是否有重复
     */
    Boolean isRepeat(String modId,String UsrSn) throws AlgException;
}