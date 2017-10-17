package com.beyond.algo.infra;

import java.util.List;
import com.beyond.algo.common.FileDir;

public interface ShowProjectFileService {
    /**
     * 展示同级目录所有文件和文件夹.
     */
    List<FileDir> ShowProjectFile(String path);
}
