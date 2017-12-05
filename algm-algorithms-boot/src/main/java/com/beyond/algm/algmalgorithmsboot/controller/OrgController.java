package com.beyond.algm.algmalgorithmsboot.controller;

import com.beyond.algm.algmalgorithmsboot.base.BaseController;
import com.beyond.algm.algmalgorithmsboot.infra.OrgService;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.OrgVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Result<AlgUser> createOrg(AlgUser org) throws AlgException {
        log.info("创建组织：组织账户名:{},组织全名:{}", org.getUsrCode(), org.getUsrName());
        org = orgService.createOrg(org, getUserInfo());
        return Result.ok(org);
    }

    /**
     * 删除组织
     *
     * @param orgSn 组织串号
     * @return 是否成功
     */
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Boolean> deleteOrg(String orgSn) throws AlgException {
        orgService.deleteOrg(orgSn);
        return Result.ok(true);
    }

    /**
     * 编辑组织
     *
     * @param org 组织
     * @return 创建后组织对象
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public Result<AlgUser> updateOrg(AlgUser org) throws AlgException {
        log.info("编辑组织：组织账户名:{},组织全名:{}", org.getUsrCode(), org.getUsrName());
        org = orgService.updateOrg(org);
        return Result.ok(org);
    }

    /**
     * 获取当前登录用户组织列表
     *
     * @param pageable 分页信息
     * @return 组织列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<PageInfo<OrgVo>> getOrgList(@PageableDefault Pageable pageable) throws AlgException {
        AlgUser currentUser = getUserInfo();
        PageInfo<OrgVo> orgList = orgService.getOrgList(currentUser.getUsrSn(), pageable);
        return Result.ok(orgList);
    }

    /**
     * 根据组织串号获取组织详情
     *
     * @param orgCode 组织编码
     * @return 组织详情
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Result<OrgVo> getOrgDetail(String orgCode) throws AlgException {
        OrgVo org = orgService.getOrgDetail(orgCode);
        return Result.ok(org);
    }

    /**
     * 将用户添加到组织中
     *
     * @param orgSn    组织串号
     * @param memberSn 添加进组织的用户串号
     * @return 是否成功
     */
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> addMember(String orgSn, String memberSn) throws AlgException {
        orgService.addMember(orgSn, memberSn);
        return Result.ok(true);
    }

    /**
     * 将用户从组织中移除
     *
     * @param orgSn    组织串号
     * @param memberSn 从组织中移除的用户串号
     * @return 是否成功
     */
    @RequestMapping(value = "/removeMember", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> removeMember(String orgSn, String memberSn) throws AlgException {
        orgService.removeMember(orgSn, memberSn);
        return Result.ok(true);
    }
}
