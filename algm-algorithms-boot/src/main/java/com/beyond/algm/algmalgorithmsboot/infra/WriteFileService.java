package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;

public interface WriteFileService {

    /**
     * 内容写入文件.
     */
    void writeFile(String usrCode, String modId, String path, String fileName,String fileContent) throws AlgException;
}
