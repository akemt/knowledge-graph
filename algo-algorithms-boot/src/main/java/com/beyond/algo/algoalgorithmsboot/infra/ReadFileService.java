package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.vo.AlgFileReadWriteVo;

public interface ReadFileService {

    /**
     * 返回文件内容.
     */
    AlgFileReadWriteVo readFile(String usrCode, String modId, String path, String fileName) throws AlgException;
}
