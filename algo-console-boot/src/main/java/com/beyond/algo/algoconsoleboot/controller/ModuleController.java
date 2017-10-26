package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.base.BaseController;
import com.beyond.algo.algoconsoleboot.infra.JGitService;
import com.beyond.algo.algoconsoleboot.infra.ModuleService;
import com.beyond.algo.algoconsoleboot.model.GitUser;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AlgModuleEditVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //初始化、和返回上一级的目录
    @GetMapping("/{modId}")
    public Result initTree(@PathVariable("modId") String modId,String path) {
        try {
            log.info("get module file tree: {} ",modId);
            AlgUser algUser = getUserInfo();
            AlgModuleEditVo algModuleEditVo = moduleService.AlgModule(algUser.getUsrCode(),algUser.getUsrSn(),modId,path);
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
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public @ResponseBody
    Result commitAndPushDelAllFiles(GitUser gitUser) {
        try {
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
}