package com.beyond.algo.infra.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.beyond.algo.infra.WriteFileService;
import org.springframework.stereotype.Service;

@Service
public class WriteFileServiceImpl implements WriteFileService{
    @Override
    public void writeFileString(String content,String path) {
        // 构建指定文件
        File file = new File(path);
        OutputStream out = null;
        try {
            // 根据文件创建文件的输出流
            out = new FileOutputStream(file);

            //读取内容
            String message = content;

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
