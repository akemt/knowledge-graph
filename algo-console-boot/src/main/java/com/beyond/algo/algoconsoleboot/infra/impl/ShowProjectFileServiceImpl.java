package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.common.FileDir;
import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowProjectFileServiceImpl implements ShowProjectFileService{
    @Override
    public List<FileDir> ShowProjectFile(String path) {
        //创建File对象
        File file = new File(path);
        String currentPath = path;
        //同级下所有文件和文件夹
        List<FileDir> fileList = new ArrayList<FileDir>();

        //获取当前目录的上一级
        if(countGs(path)>1){//到跟目录为止
            currentPath = path.substring(0,path.lastIndexOf("/"));
        }else {
           //todo 到根目录时候再点击处理
        }
        //获取该目录下的所有文件
        String[] files = file.list();
        for (String f : files){
            FileDir rootFileDir=new FileDir();
            rootFileDir.setName(f);
            if(countGs(path)==1){
                rootFileDir.setUrl(path + f);
            }else {
                rootFileDir.setUrl(path + "/" + f);
            }
            rootFileDir.setCurrentPath(currentPath);
            fileList.add(rootFileDir);
            System.out.println(f);
        }
        System.out.println(path);
        return fileList;
    }

    /**
     * 查找path中'/'的个数
     * @param ：path 路径
     */
    private int countGs(String path) {
        String s = path;
        char c = '/';
        int num = 0;
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++)
        {
            if(c == chars[i])
            {
                num++;
            }
        }
        return num;
    }
}
