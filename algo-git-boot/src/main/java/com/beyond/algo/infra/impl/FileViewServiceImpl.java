package com.beyond.algo.infra.impl;

import com.beyond.algo.common.FileUtil;
import com.beyond.algo.infra.FileViewService;
import com.beyond.algo.model.FileDir;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:展示文件树状结构
 * @date ：16:01 2017/9/29
 */
/*@Service
public class FileViewServiceImpl implements FileViewService {
  @Override
    public List<FileDir> showFileTree(String path){
      File filePath =new File(path);
      List<FileDir> fileDirList= FileUtil.getPath(filePath);
      return fileDirList;
    }

}*/
