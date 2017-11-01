package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.common.FileNodes;

public interface ShowProjectFileService {
    /**
     * 展示同级目录所有文件和文件夹.
     * @param currentPath
     * @param usrCode
     * @param modId
     * @return
     * @throws Exception
     */
    FileNodes ShowProjectFile(String currentPath, String usrCode, String modId) throws Exception;

    /**
     * 获取当前用户 项目为modId的服务器路径
     * @param usrCode
     * @param modId
     * @return
     * @throws Exception
     */
    String getSplitPath(String usrCode,String modId) throws Exception ;
}
