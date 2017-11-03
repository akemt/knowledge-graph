package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.base.BaseController;
import com.beyond.algo.algoconsoleboot.infra.*;
import com.beyond.algo.algoconsoleboot.model.GitUser;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigEntity;
import com.beyond.algo.common.AESUtil;
import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AddAlgorithmVo;
import com.beyond.algo.vo.AlgModuleEditVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;

/**
 * @author ：lindewei
 * @Description:目录tree操作
 * @date ：16:30 2017/10/12
 */
@RestController
@RequestMapping("/module")
@Slf4j
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private JGitService jGitService;
    @Autowired
    private AntApiService antApiService;
    @Autowired
    private AddAlgorithmService addAlgorithmService;

    @Autowired
    private ProjectConfigEntity projectConfigEntity;

    @Autowired
    private GitLibService gitLibService;

    //初始化、和返回上一级的目录
    @GetMapping(value = "/{modId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result initTree(@PathVariable("modId") String modId,String path,String fileName) {
            try {
            log.info("get module file tree: {} ",modId);

            // path为"/" 并且 fileName不为空
            if("/".equals(path)&&Assert.isNotEmpty(fileName)){
                path = path+fileName;
            }else {
                // 1、path有目录时候，fileName不为空；2、或者path为"/"，fileName为空
                path = path+File.separator+fileName;
            }

            AlgUser algUser = getUserInfo();
            AlgModuleEditVo algModuleEditVo = moduleService.algModule(algUser.getUsrCode(),algUser.getUsrSn(),modId,path);
            return Result.ok(algModuleEditVo);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @param :gitUser
     * @return
     * @Description:删除本地文件同时同步服务器 author:zhangchuanzhi
     */
    @RequestMapping(value = "/{modId}/del", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result commitAndPushDelAllFiles(GitUser gitUser,@PathVariable("modId") String modId) {
        try {
            gitUser.setModId(modId);
            AlgUser algUser = getUserInfo();
            gitUser.setUsrCode(algUser.getUsrCode());
            boolean result = jGitService.commitAndPushDelAllFiles(gitUser);
            if (result) {
                return Result.successResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.failureResponse();
    }

    /**
     * @param :gitUser
     * @return
     * @Description:ant项目进行编译打包同时解压到指定目录并且代码上传git上
     * author:zhangchuanzhi
     */
    @RequestMapping(value = "/{modId}/buildProject", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result buildAndUpLoadProject(GitUser gitUser,@PathVariable("modId") String modId) throws AlgException,Exception {
        gitUser.setModId(modId);
        AlgUser algUser = getUserInfo();
     //   gitUser.setUsrCode(algUser.getUsrCode());
        gitUser.setUsrCode(algUser.getUsrCode());
        gitUser.setPassword(  AESUtil.decryptAES(algUser.getPasswd(),projectConfigEntity.getKeyAES()));
        log.info("用户名字:{},用户密码:{} ",gitUser.getUsrCode(),gitUser.getUsrCode());
        boolean result = antApiService.moduleAntBuild(gitUser);
        if (result) {
            return Result.successResponse();
        }
        return Result.failureResponse();
    }


    /**
     * @author ：lindewei
     * @Description:算法新增
     * @param：User
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result AddAlgorithm(AddAlgorithmVo addAlgorithmVo) throws AlgException {
        AlgUser algUser = getUserInfo();
        addAlgorithmVo.setUsrSn(algUser.getUsrSn());
        try{
            addAlgorithmService.addAlgorithm(addAlgorithmVo);
            GitUser gitUser = new GitUser();
            gitUser.setModId(addAlgorithmVo.getModId());
            gitUser.setUsrCode(algUser.getUsrCode());
            gitUser.setPassword(algUser.getPasswd());
            gitLibService.createGitLibProject(gitUser);
        }catch (Exception e){
            throw new AlgException(e.getMessage());
        }

        return Result.successResponse();
    }
}
