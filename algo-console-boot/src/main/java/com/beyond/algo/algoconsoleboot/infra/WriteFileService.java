package com.beyond.algo.algoconsoleboot.infra;

public interface WriteFileService {

    /**
     * 内容写入文件.
     */
    void writeFile(String usrCode, String modId, String path, String fileName,String fileContent) throws Exception;
}
