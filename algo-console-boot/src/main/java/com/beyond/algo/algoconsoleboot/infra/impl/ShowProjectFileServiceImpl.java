package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigModel;
import com.beyond.algo.common.FileNode;
import com.beyond.algo.common.FileNodes;
import com.beyond.algo.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class ShowProjectFileServiceImpl implements ShowProjectFileService{
    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private ProjectConfigModel projectConfigModel;

    public String getModuleBasePath(String usrCode, String modId) throws AlgException {
        //项目名称初始化Tree
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gitConfigModel.getLocalBasePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(usrCode);
        stringBuilder.append(File.separator);
        stringBuilder.append(modId);
        return stringBuilder.toString();
    }
    @Override
    public FileNodes ShowProjectFile(String currentPath,String usrCode,String modId) throws AlgException{

        log.info("ShowProjectFile currentPath :{}",currentPath);
        // 获取用户下项目根路径
        String splitPath = getModuleBasePath(usrCode,modId);
        //转换标注路径
        File file = new File(currentPath);
        currentPath = file.getPath();

        FileNodes fileNodes = new FileNodes();

        if(currentPath.equals(splitPath)){
            fileNodes.setCurrentPath(File.separator);
        }else{
            fileNodes.setCurrentPath(currentPath.substring(splitPath.length(),currentPath.length()));
        }
        //判断当根目录情况下， 设置父节点为跟目录
        if(currentPath.equals(splitPath)){
            fileNodes.setPatentPath(File.separator);
        }else if(file.getParent().equals(splitPath)){// /src情况下，设置父节点为跟目录
            fileNodes.setPatentPath(File.separator);
        }else{//其他情况均为自己的父目录
            fileNodes.setPatentPath(file.getParent().substring(splitPath.length(),file.getParent().length()));
        }

        log.info("ShowProjectFile currentPath and patentPath:{}",fileNodes.getCurrentPath(),fileNodes.getPatentPath());
        List<FileNode> nodeList = new LinkedList<FileNode>();
        String[] files = file.list();
        for (String f : files){
            File temp = new File(currentPath+ File.separator+f);
            FileNode fileNode = new FileNode();
            fileNode.setFileName(f);
            fileNode.setDir(temp.isDirectory());
            nodeList.add(fileNode);
        }
        fileNodes.setNodeList(nodeList);
       return fileNodes;
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
