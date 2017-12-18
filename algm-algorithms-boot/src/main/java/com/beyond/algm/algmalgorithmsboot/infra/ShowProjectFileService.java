package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.common.FileNodes;
import com.beyond.algm.exception.AlgException;

public interface ShowProjectFileService {
    /**
     * 展示同级目录所有文件和文件夹.
     *
     * @param currentPath
     * @param curUsrCode 当前登录用户的Code
     * @param modId  算法Code
     * @param usrCode  用户Code或者组织Code
     * @param isOrg  1-组织；0-用户
     * @return
     * @throws AlgException
     */
    FileNodes ShowProjectFile(String currentPath, String curUsrCode, String modId,String usrCode,String isOrg) throws AlgException;


}
