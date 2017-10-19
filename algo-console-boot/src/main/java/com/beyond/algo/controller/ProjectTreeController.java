package com.beyond.algo.controller;

import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.infra.ShowProjectFileService;
import com.beyond.algo.model.GitConfigModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.beyond.algo.infra.ReadFileService;
import com.beyond.algo.common.FileDir;

/**
 * @author ：lindewei
 * @Description:目录tree操作
 * @date ：16:30 2017/10/12
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/tree")
public class ProjectTreeController {
    @Autowired
    private ShowProjectFileService showProjectFileService;
    @Autowired
    private ReadFileService readFileService;
    @Autowired
    private GitConfigModel gitConfigModel;

    /**
     * 展示同级目录所有文件和文件夹，或者展示文本。
     * author:lindewei
     * @param ：path 同级目录路径
     * @return ：返回文件内容，或者展示文本。
     */
    @RequestMapping(value="/showFile", method= RequestMethod.GET)
    public @ResponseBody
    Result showFile(String path) {
        List<FileDir> showProjectFileList = null;
        try {
            //如果点击的是文本
            //TODO 获取用户英文名称和项目名称
            path = gitConfigModel.getLocalBasePath()+"/"+"test1/"+"TestProject/"+path ;
            File file = new File(path);
            if(file.isDirectory()){
                showProjectFileList= new ArrayList<>();
                //返回同级目录所有文件和文件夹.
                showProjectFileList = showProjectFileService.ShowProjectFile(path);
            }else{
                //说明是文本，直接取出path，进行文本的初始化展示。
                readFileService.readFileString(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.ok(showProjectFileList);
    }

    /**
     * tree目录返回
     * author:lindewei
     * @param ：path 同级目录路径
     * @return ：返回文件内容，或者展示文本。
     */
    @RequestMapping(value="/backFile", method= RequestMethod.POST)
    public @ResponseBody
    Result backFile(String path) {
        try {
            //如果点击的是文本
            File file = new File(path);
            if(file.isDirectory()){
                List<FileDir> showProjectFileList = new ArrayList<>();
                //返回同级目录所有文件和文件夹.
                showProjectFileList = showProjectFileService.ShowProjectFile(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.successResponse();
    }
}
