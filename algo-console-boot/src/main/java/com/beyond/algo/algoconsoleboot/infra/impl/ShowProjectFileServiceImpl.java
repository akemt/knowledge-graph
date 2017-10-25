package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigModel;
import com.beyond.algo.common.FileDir;
import com.beyond.algo.common.FileNode;
import com.beyond.algo.common.FileNodes;
import com.beyond.algo.common.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.beyond.algo.common.StringConstant.src;

@Service
public class ShowProjectFileServiceImpl implements ShowProjectFileService{
    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private ProjectConfigModel projectConfigModel;

    public String getSplitPath(String usrCode,String modId) throws Exception {
        //TODO 项目名称初始化Tree
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(gitConfigModel.getLocalBasePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(usrCode);
        stringBuilder.append(File.separator);
        stringBuilder.append(modId);
        return stringBuilder.toString();
    }
    @Override
    public FileNodes ShowProjectFile(String currentPath,String usrCode,String modId) throws Exception{
        String splitPath = getSplitPath(usrCode,modId);
        File file = new File(currentPath);
        currentPath = file.getPath();

        FileNodes fileNodes = new FileNodes();
        fileNodes.setCurrentPath(currentPath.substring(splitPath.length(),currentPath.length()));
        if(file.getParent().equals(new File(splitPath).getPath())){
            fileNodes.setPatentPath("/");
        }else{
            fileNodes.setPatentPath(file.getParent().substring(splitPath.length(),file.getParent().length()));
        }

        List<FileNode> nodeList = new LinkedList<FileNode>();
        //TODO file.list(FilenameFilter filter)
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

    /*public List<FileDir> ShowProjectFile(String path) {
        //创建File对象
        File file = new File(path);
        String currentPath = path;
        //同级下所有文件和文件夹
        List<FileDir> fileList = new ArrayList<FileDir>();

        //获取当前目录的上一级
        if(countGs(path)>1){//到跟目录为止
            currentPath = path.substring(0,path.lastIndexOf("/"));
        }
        //获取该目录下的所有文件
        String[] files = file.list();
        for (String f : files){
            FileDir rootFileDir=new FileDir();
            rootFileDir.setName(f);
            //if(countGs(path)==1){
            //    rootFileDir.setUrl(path + f);
            //}else {
            //   rootFileDir.setUrl(path + "/" + f);
            //}
            rootFileDir.setUrl(path + f);
            rootFileDir.setCurrentPath(currentPath);
            fileList.add(rootFileDir);
            System.out.println(f);
        }
        System.out.println(path);
        return fileList;
    }*/

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
