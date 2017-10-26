package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.base.BaseController;
import com.beyond.algo.algoconsoleboot.infra.ModuleService;
import com.beyond.algo.algoconsoleboot.infra.ReadFileService;
import com.beyond.algo.algoconsoleboot.infra.ShowProjectFileService;
import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigModel;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AlgModuleEditVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private ShowProjectFileService showProjectFileService;
    @Autowired
    private ReadFileService readFileService;
    @Autowired
    private GitConfigModel gitConfigModel;
    @Autowired
    private ProjectConfigModel projectConfigModel;
    @Autowired
    private ModuleService moduleService;

    //初始化、和返回上一级的目录
    @GetMapping("/{modId}")
    public Result initTree(@PathVariable("modId") String modId,String path) {
        try {
            AlgUser algUser = getUserInfo();
            AlgModuleEditVo algModuleEditVo = moduleService.AlgModule(algUser.getUsrCode(),algUser.getUsrSn(),modId,path);
            return Result.ok(algModuleEditVo);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
}
