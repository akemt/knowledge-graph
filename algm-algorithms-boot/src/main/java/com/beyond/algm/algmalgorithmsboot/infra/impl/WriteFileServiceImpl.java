package com.beyond.algm.algmalgorithmsboot.infra.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.beyond.algm.algmalgorithmsboot.infra.PathService;
import com.beyond.algm.algmalgorithmsboot.infra.ShowProjectFileService;
import com.beyond.algm.algmalgorithmsboot.infra.WriteFileService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WriteFileServiceImpl implements WriteFileService {
    @Autowired
    private ShowProjectFileService showProjectFileService;
    @Autowired
    private PathService pathService;
    @Override
    public void writeFile(String modPath, String path, String fileName,String fileContent) throws AlgException {
        String writePath = null;
        try {
            if(Assert.isEmpty(path)){
                writePath = modPath + File.separator + fileName;
            }else {
                writePath = modPath + File.separator +path + File.separator + fileName;
            }
        } catch (Exception e) {
            throw new AlgException("BEYOND.ALG.MODULE.WRITE.0000009",new String[]{},e);
        }
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
