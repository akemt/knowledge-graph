package com.beyond.algo.common;

import java.io.*;

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
     * 指定文件夹路径，若不存在则创建文件夹
     *
     * @param dirPath 文件夹路径
     */
    public static void createDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

}
