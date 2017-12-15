package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;

public interface WriteFileService {

    /**
     * 算法编辑-写入文件
     *
     * @param modPath
     * @param path
     * @param fileName
     * @param fileContent
     * @throws AlgException
     */
    void writeFile(String modPath, String path, String fileName,String fileContent) throws AlgException;
}
