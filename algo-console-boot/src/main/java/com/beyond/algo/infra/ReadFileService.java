package com.beyond.algo.infra;

import java.io.File;

public interface ReadFileService {

    /**
     * 返回文件内容.
     */
    String readFileString(File file);
}
