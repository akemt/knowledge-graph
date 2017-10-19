package com.beyond.algo.controller;

import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.infra.OrgService;
import com.beyond.algo.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/org")
public class OrgController {

    @Autowired
    private OrgService orgService;

    /**
     * 创建组织
     *
     * @param org 组织
     * @return 创建后组织对象
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result<AlgUser> createOrg(AlgUser org, HttpSession session) {
        log.info("创建组织：组织账户名:{},组织全名:{},用户ID:{}", org.getUsrCode(), org.getUsrName(), org.getOwnerId());
        try {
            //TODO:单点登录可能调整用户名密码获取方式
            String userCode = (String) session.getAttribute("userCode");
            String password = (String) session.getAttribute("userPwd");

            //TODO: 测试代码，注入用户信息
            userCode = "qihe";
            password = "12345678";

            org = orgService.createOrg(org, userCode, password);
            return Result.ok(org);
        } catch (Exception e) {
            log.error("创建组织失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
}
