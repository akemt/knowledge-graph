package com.beyond.algo.controller;

import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageUploadController {
    /**
      * 文件上传根目录(在Spring的application.properties 的配置文件中配置)
      *  upload-path: （jar包所在目录）/resources/static/
      */
    @Value("${image.upload-path}")
    private String imageUploadPath;

    /**
     * 基于用户标识的头像上传
     * @param file 图片
     * @param userId 用户标识
     * @return
     */
    @PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId) {
        Result resultVo = new Result();
        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")) {
                try {
                    String temp = "images" + File.separator + "upload" + File.separator;
                    // 获取图片的文件名
                    String fileName = file.getOriginalFilename();
                    // 获取图片的扩展名
                    String extensionName = StringUtils.substringAfter(fileName, ".");
                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                    // 数据库保存的目录
                    String datdDirectory = temp.concat(String.valueOf(userId)).concat(File.separator);
                    // 文件路径
                    String filePath = imageUploadPath.concat(datdDirectory);

                    File dest = new File(filePath, newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }

                    // 上传到指定目录
                    file.transferTo(dest);

                    // 将图片流转换进行BASE64加码
                    //BASE64Encoder encoder = new BASE64Encoder();
                    //String data = encoder.encode(file.getBytes());

                    // 将反斜杠转换为正斜杠
                    String data = datdDirectory.replaceAll("\\\\", "/") + newFileName;
                    resultVo.setCode(ResultEnum.SUCCESS.code);
                    resultVo.setMsg("上传成功！");
                } catch (IOException e) {
                    resultVo.setCode(ResultEnum.UNKNOWN_ERROR.code);
                    resultVo.setMsg("上传失败！");
                }
            } else {
                resultVo.setCode(ResultEnum.PARAM_ERROR.code);
                resultVo.setMsg("上传的文件不是图片类型，请重新上传!");
            }
            return resultVo;
        } else {
            resultVo.setCode(ResultEnum.SUCCESS.code);
            resultVo.setMsg("上传失败！");
            return resultVo;
        }
    }
}
