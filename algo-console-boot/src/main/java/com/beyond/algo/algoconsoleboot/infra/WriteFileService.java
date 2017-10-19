package com.beyond.algo.algoconsoleboot.infra;

public interface WriteFileService {

    /**
     * 内容写入文件.
     */
    void writeFileString(String content,String path);
}
