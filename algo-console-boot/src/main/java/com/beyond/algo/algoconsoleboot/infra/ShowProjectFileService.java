package com.beyond.algo.algoconsoleboot.infra;

import java.util.List;
import com.beyond.algo.common.FileDir;
import com.beyond.algo.common.FileNodes;

public interface ShowProjectFileService {
    /**
     * 展示同级目录所有文件和文件夹.
     */
    FileNodes ShowProjectFile(String currentPath, String usrCode, String modId) throws Exception;

    /**
     *
     * @param usrCode
     * @param modId
     * @return
     * @throws Exception
     */
    String getSplitPath(String usrCode,String modId) throws Exception ;
}
