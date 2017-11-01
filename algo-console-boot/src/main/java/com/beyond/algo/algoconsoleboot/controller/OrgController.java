package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.base.BaseController;
import com.beyond.algo.algoconsoleboot.infra.OrgService;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/org")
public class OrgController extends BaseController {

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
    public Result<AlgUser> createOrg(AlgUser org) throws AlgException {
        log.info("创建组织：组织账户名:{},组织全名:{},用户ID:{}", org.getUsrCode(), org.getUsrName(), org.getOwnerId());
        AlgUser algUser = getUserInfo();
        org = orgService.createOrg(org, algUser.getUsrCode(), algUser.getPasswd());
        return Result.ok(org);
    }

    /**
     * 删除组织
     *
     * @param orgSn 组织串号
     * @return 是否成功
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> deleteOrg(String orgSn) throws AlgException {
        orgService.deleteOrg(orgSn);
        return Result.ok(true);
    }
}
