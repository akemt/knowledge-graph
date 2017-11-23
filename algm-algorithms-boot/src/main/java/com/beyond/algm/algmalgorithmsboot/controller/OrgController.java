package com.beyond.algm.algmalgorithmsboot.controller;

import com.beyond.algm.algmalgorithmsboot.base.BaseController;
import com.beyond.algm.algmalgorithmsboot.infra.OrgService;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.OrgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * 根据用户串号获取
     * @param usrSn 用户串号
     * @return 组织列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<OrgVo>> getOrgList(String usrSn) throws AlgException {
        List<OrgVo> orgList = orgService.getOrgList(usrSn);
        return Result.ok(orgList);
    }

    /**
     * 将用户添加到组织中
     *
     * @param orgSn    组织串号
     * @param memberSn 添加进组织的用户串号
     * @return 是否成功
     */
    public Result<Boolean> addMember(String orgSn, String memberSn) throws AlgException {
        orgService.addMember(orgSn, memberSn);
        return Result.ok(true);
    }
}
