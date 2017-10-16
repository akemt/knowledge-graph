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

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/org")
public class OrgController {

    @Autowired
    private OrgService orgService;

    /**
     *创建组织
     * @param org 组织
     * @return 创建后组织对象
     */
    @RequestMapping(value="/create", method=RequestMethod.POST)
    @ResponseBody
    public Result<Object> createOrg(@Valid AlgUser org){
        log.info("创建组织：组织账户名:{0},组织全名:{1},用户ID:{2}", org.getUsrCode(), org.getUsrName(), org.getOwnerId());
        try {
            org = orgService.createOrg(org);
            return null;
        } catch (Exception e) {
            log.error("创建组织失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
}
