package com.beyond.algo.algoconsoleboot.infra;

import java.io.File;

public interface ReadFileService {

    /**
     * 返回文件内容.
     */
    String readFileString(File file);
}
