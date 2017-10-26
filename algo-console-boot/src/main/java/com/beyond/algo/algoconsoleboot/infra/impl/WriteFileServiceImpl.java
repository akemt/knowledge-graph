package com.beyond.algo.algoconsoleboot.infra.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import com.beyond.algo.algoconsoleboot.infra.WriteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WriteFileServiceImpl implements WriteFileService {
    @Autowired
    private ShowProjectFileService showProjectFileService;
    @Override
    public void writeFile(String usrCode, String modId, String path, String fileName,String fileContent) throws Exception{
        String writePath = showProjectFileService.getSplitPath(usrCode,modId) + "/"+path +"/"+ fileName;//正式
        //String writePath = showProjectFileService.getSplitPath(usrCode,modId) + "//"+"src//beyondalgo//TestJava" +"//"+ "TestJava111.java";//测试
        // 构建指定文件
        File file = new File(writePath);
        OutputStream out = null;
        try {
            // 根据文件创建文件的输出流
            out = new FileOutputStream(file);

            //读取内容
            String message = fileContent;

            // 把内容转换成字节数组
            byte[] data = message.getBytes();
            // 向文件写入内容
            out.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭输出流
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
