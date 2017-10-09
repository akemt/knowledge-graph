package com.beyond.algo.infra;

import com.beyond.algo.model.FileDir;

import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:实现目录文件读取
 * @date ：15:34 2017/9/29
 */
public interface FileViewService {
    List<FileDir> showDirectoryAndFile(String path);

}
