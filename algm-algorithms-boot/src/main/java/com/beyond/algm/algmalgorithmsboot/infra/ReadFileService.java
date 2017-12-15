package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.vo.AlgFileReadWriteVo;

public interface ReadFileService {

    /**
     * 算法编辑-读取文件信息
     *
     * @param modPath
     * @param path
     * @param fileName
     * @return
     * @throws AlgException
     */
    AlgFileReadWriteVo readFile(String modPath, String path, String fileName) throws AlgException;
}
