package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.base.BaseController;
import com.beyond.algo.algoconsoleboot.infra.ModuleService;
import com.beyond.algo.algoconsoleboot.infra.ReadFileService;
import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigModel;
import com.beyond.algo.common.*;
import com.beyond.algo.model.AlgModule;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AlgModuleEditVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：lindewei
 * @Description:目录tree操作
 * @date ：16:30 2017/10/12
 */
@RestController
@RequestMapping("/tree")
@Slf4j
public class ProjectTreeController extends BaseController {
    @Autowired
    private ShowProjectFileService showProjectFileService;
    @Autowired
    private ReadFileService readFileService;
    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private ProjectConfigModel projectConfigModel;
    @Autowired
    private ModuleService moduleService;

    @GetMapping("/{modId}")
    public Result initTree(@PathVariable("modId") String modId,String path) {

        AlgModuleEditVo algModuleEditVo = new AlgModuleEditVo();
        try {
            AlgUser algUser = getUserInfo();
            log.info("current user:{} ",algUser.getUsrCode());
            AlgModule algModule = moduleService.findByUsrSnAndModId(algUser.getUsrSn(),modId);
            log.info("current project id:{} ,name :{} ",algModule.getModId(),algModule.getModName());
            //项目名称初始化Tree
            if (Assert.isEmpty(path)){
                path = moduleService.getModuleMainFilePath(algUser.getUsrCode(),modId,algModule.getLanSn());
            }else{
                path = showProjectFileService.getSplitPath(algUser.getUsrCode(),modId)+"/"+path;
            }

            log.info("current path {} ",path);
            //返回同级目录所有文件和文件夹.
            FileNodes fileNodes = showProjectFileService.ShowProjectFile(path,algUser.getUsrCode(),modId);
            log.info("current fileNodes {} ",fileNodes.toString());
            algModuleEditVo.setModId(algModule.getModId());
            algModuleEditVo.setModName(algModule.getModName());
            algModuleEditVo.setLatestCommit("b975b7748b90f259240156c6e39129f058ebb141");
            algModuleEditVo.setLatestVersion("0.0.3");
            algModuleEditVo.setFileNodes(fileNodes);
            return Result.ok(algModuleEditVo);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * 展示同级目录所有文件和文件夹，或者展示文本。
     * author:lindewei
     *
     * @param ：path 同级目录路径
     * @return ：返回文件内容，或者展示文本。
     */
    @RequestMapping(value = "/showFile", method = RequestMethod.GET)
    public Result showFile(String path) {
        List<FileDir> showProjectFileList = null;
        try {
            //如果点击的是文本
            //TODO 获取用户英文名称和项目名称
            path = gitConfigModel.getLocalBasePath() + "/" + "test1/" + "TestProject/" + path;
            File file = new File(path);
            if (file.isDirectory()) {
                showProjectFileList = new ArrayList<>();
                //返回同级目录所有文件和文件夹.
                //showProjectFileList = showProjectFileService.ShowProjectFile(path);
            } else {
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
     *
     * @param ：path 同级目录路径
     * @return ：返回文件内容，或者展示文本。
     */
    @RequestMapping(value = "/backFile", method = RequestMethod.POST)
    public Result backFile(String path) {
        try {
            //如果点击的是文本
            File file = new File(path);
            if (file.isDirectory()) {
                List<FileDir> showProjectFileList = new ArrayList<>();
                //返回同级目录所有文件和文件夹.
                //showProjectFileList = showProjectFileService.ShowProjectFile(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.successResponse();
    }
}
