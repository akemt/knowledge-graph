package com.beyond.algm.algmfileboot.util;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author ：zhangchuanzhi
 * @Description:文件下载共同
 * @date ：9:21 2017/12/13
 */
public class FileDownLoad {

    /**
     * 文件下载
     *
     * @param
     */
    public static void fileDownLoad(HttpServletResponse response,File downloadFile,String fileName) {
        //设置响应头和客户端保存文件名
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        try {
            //打开本地文件流
            InputStream inputStream = new FileInputStream(downloadFile);
            //激活下载操作
            OutputStream os = response.getOutputStream();

            //循环写入输出流
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (Exception e){
        }
    }
}
