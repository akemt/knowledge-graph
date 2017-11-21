package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.common.FileNodes;
import com.beyond.algm.exception.AlgException;

public interface ShowProjectFileService {
    /**
     * 展示同级目录所有文件和文件夹.
     * @param currentPath
     * @param usrCode
     * @param modId
     * @return
     * @throws Exception
     */
    FileNodes ShowProjectFile(String currentPath, String usrCode, String modId) throws AlgException;

    /**
     * 获取当前用户项目为modId的服务器项目根路径
     * @param usrCode
     * @param modId
     * @return
     * @throws Exception
     */
    String getModuleBasePath(String usrCode, String modId) throws AlgException ;
}
