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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrgController extends BaseController {

    @Autowired
    private OrgService orgService;

    /**
     * 创建组织
     *
     * @param org 组织
     * @return 创建后组织对象
     */
    @RequestMapping(value = "/org", method = RequestMethod.POST)
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
    @RequestMapping(value = "/org", method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Boolean> deleteOrg(String orgSn) throws AlgException {
        orgService.deleteOrg(orgSn, getUserInfo());
        return Result.ok(true);
    }

    /**
     * 编辑组织
     *
     * @param org 组织
     * @return 创建后组织对象
     */
    @RequestMapping(value = "/org", method = RequestMethod.PUT)
    @ResponseBody
    public Result<AlgUser> updateOrg(AlgUser org) throws AlgException {
        log.info("编辑组织：组织账户名:{},组织全名:{}", org.getUsrCode(), org.getUsrName());
        org = orgService.updateOrg(org, getUserInfo());
        return Result.ok(org);
    }

    /**
     * 获取当前登录用户组织列表
     *
     * @param pageInfo 分页信息
     * @return 组织列表
     */
    @RequestMapping(value = "/org/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<PageInfo<OrgVo>> getOrgList(PageInfo pageInfo) throws AlgException {
        pageInfo.setPageNum(pageInfo.getPageNum()==0?1 : pageInfo.getPageNum());
        pageInfo.setPageSize(pageInfo.getPageSize()==0?10 : pageInfo.getPageSize());
        AlgUser currentUser = getUserInfo();
        PageInfo<OrgVo> orgList = orgService.getOrgList(currentUser.getUsrSn(), pageInfo);
        return Result.ok(orgList);
    }

    /**
     * 根据组织串号获取组织详情
     *
     * @param orgCode 组织编码
     * @return 组织详情
     */
    @RequestMapping(value = "/org", method = RequestMethod.GET)
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
    @RequestMapping(value = "/org/addMember", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> addMember(String orgSn, String memberSn) throws AlgException {
        orgService.addMember(orgSn, memberSn, getUserInfo());
        return Result.ok(true);
    }

    /**
     * 将用户从组织中移除
     *
     * @param orgSn    组织串号
     * @param memberSn 从组织中移除的用户串号
     * @return 是否成功
     */
    @RequestMapping(value = "/org/removeMember", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> removeMember(String orgSn, String memberSn) throws AlgException {
        orgService.removeMember(orgSn, memberSn, getUserInfo());
        return Result.ok(true);
    }
}
