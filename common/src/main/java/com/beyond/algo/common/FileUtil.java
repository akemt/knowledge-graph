package com.beyond.algo.common;

import com.beyond.algo.model.FileDir;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 将内容回写到文件中
     *
     * @param filePath 文件路径
     * @param content  文件内容
     */
    public static void writeFile(String filePath, String content) {
        try {
            //写入的文本不附加在原来的后面而是直接覆盖
            //   Writer writeFile=new FileWriter("E:/repo/test1/TestProject/src/algorithmia/test_java/testTest_java.java",false);//写入的文本不附加在原来的后面而是直接覆盖
            Writer writeFile = new FileWriter(filePath);
            String str = content;
            ;
            writeFile.write(str);
            writeFile.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }

    /**
     * 删除文件
     *
     * @param path 路径
     */
    public static void delFile(String path) {
        File file = new File(path);
        file.delete();

    }

    /**
     * 查找文件是否存在
     *
     * @param path 文件路径
     */
    public static boolean searchFile(String path) {
        //   File file = new File("E:\\gitTest\\algorithm.zip");
        File file = new File(path);
        return file.exists();
    }

    /**
     * 指定文件或文件夹路径，若不存在则创建
     *
     * @param dirPath 路径
     */
    public static void createDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 获得文件路径
     *
     * @param ：File f
     */
    public static List<FileDir> getPath(File f) {
        String filePath=f.getAbsolutePath();
        // 获得跟目录
        String fileDir= filePath.substring(filePath.indexOf(File.separator)+1,filePath.indexOf(File.separator,3));
        // 判断是否是跟目录
        int i=f.getParent().indexOf(File.separator,3);
        List<FileDir> fileDirList=new ArrayList<FileDir>();
        if(i>0){
            File fileFather =new File(f.getParent());
            File fileGrandFather =new File(fileFather.getParent());
            tree(fileGrandFather,fileDirList);
        }else{
            FileDir rootFileDir=new FileDir();
            rootFileDir.setName(fileDir);
            rootFileDir.setUrl(filePath);
            fileDirList.add(rootFileDir);
        }
        return fileDirList;
    }

    /**
     * 获得目录文件名
     * @param ：dirPath 路径
     */
    private static void tree(File f,List<FileDir>fileDirList) {
        File[] childs = f.listFiles();
        for(int i=0; i<childs.length; i++) {
            FileDir fileDir=new FileDir();
            fileDir.setName(childs[i].getName());
            fileDir.setUrl(childs[i].getAbsolutePath());
            fileDirList.add(fileDir);
        }
    }
}
