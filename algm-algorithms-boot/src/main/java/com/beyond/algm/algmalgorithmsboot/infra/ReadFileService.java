package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.vo.AlgFileReadWriteVo;

public interface ReadFileService {

    /**
     * 返回文件内容.
     */
    AlgFileReadWriteVo readFile(String usrCode, String modId, String path, String fileName) throws AlgException;
}
