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
     * 读取内容保存文件当中
     * author:lindewei
     * @param ：String 读取的内容
     */
    @RequestMapping(value="/writeFile", method= RequestMethod.POST)
    public @ResponseBody
    Result writeFile(String con) {
        try {
            writeFileService.writeFileString(con);//写入文件中，并且保存到路径下。
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.successResponse();
    }

}
