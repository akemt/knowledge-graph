package com.beyond.algo.controller;

import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.infra.WriteFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.beyond.algo.infra.ReadFileService;

import java.io.File;

/**
 * @author ：lindewei
 * @Description:文件读写操作
 * @date ：16:30 2017/10/12
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/file")
public class FileController {
    @Autowired
    private ReadFileService readFileService;

    @Autowired
    private WriteFileService writeFileService;

    /**
     * 文件读取
     * author:lindewei
     * @param ：path 想要读取的文件的路径
     * @return ：返回文件内容
     */
    @RequestMapping(value="/readFile", method= RequestMethod.POST)
    public @ResponseBody
    Result readFile(String path) {
        try {
            //File file = new File("D:/JGitServiceTest.java");
            File file = new File(path);
            readFileService.readFileString(file);//返回值是一个String
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.successResponse();
    }

    /**
     * 创建新的文本、初始化读取的文本编辑内容保存
     * author:lindewei
     * @param ：String 文本内容，和文件命名
     */
    @RequestMapping(value="/writeFile", method= RequestMethod.POST)
    public @ResponseBody
    Result writeFile(String con,String filePath) {
        try {
            //TODO 创建文件时候，前端会将当前拼接好的路径传过来（路径+用户命名）。
            writeFileService.writeFileString(con,filePath);//写入文件中，并且保存到路径下。
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.successResponse();
    }

}
