package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.vo.AlgFileReadWriteVo;

import java.io.File;

public interface ReadFileService {

    /**
     * 返回文件内容.
     */
    AlgFileReadWriteVo readFile(String usrCode, String modId, String path, String fileName) throws AlgException;
}
