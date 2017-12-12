package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.alibaba.fastjson.JSONObject;
import com.beyond.algm.algmalgorithmsboot.AlgmAlgorithmsBootApplication;
import com.beyond.algm.algmalgorithmsboot.infra.OrgService;
import com.beyond.algm.algmalgorithmsboot.infra.UserService;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.OrgVo;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AlgmAlgorithmsBootApplication.class)
public class UserServiceTest {

    @Autowired
    private OrgService orgService;
    @Autowired
    private UserService userService;

    @Test
    public void createOrgTest() throws AlgException {

        AlgUser org = new AlgUser();
        org.setUsrCode("testOrg2");
        org.setUsrName("测试组织2");
        org.setEmail("test@qq.com");

        AlgUser owner = new AlgUser();
        owner.setUsrSn("64bd0e5ee1a6409f97d12c271bb8fa68");
        owner.setUsrCode("gaohaijun");
        owner.setPasswd("47c668187ec1fc010443a9372266d304");
        owner.setPrivateToken("mHN8exdYzNja1eZkXPxc");

        orgService.createOrg(org, owner);
    }

    @Test
    public void deleteOrgTest() throws AlgException {
        AlgUser owner = new AlgUser();
        owner.setUsrSn("64bd0e5ee1a6409f97d12c271bb8fa68");
        owner.setUsrCode("gaohaijun");
        owner.setPasswd("47c668187ec1fc010443a9372266d304");
        owner.setPrivateToken("mHN8exdYzNja1eZkXPxc");

        orgService.deleteOrg("8e70e57b5e104f1b9d4d4dc0b141d83b", owner);
    }

    @Test
    public void updateOrgTest() throws AlgException {
        AlgUser org = new AlgUser();
        org.setUsrCode("testOrg2");
        org.setUsrName("测试组织2");
        org.setEmail("test@qq.com");
        org.setUsrUrl("www.baidu.com");

        AlgUser owner = new AlgUser();
        owner.setUsrSn("64bd0e5ee1a6409f97d12c271bb8fa68");
        owner.setUsrCode("gaohaijun");
        owner.setPasswd("47c668187ec1fc010443a9372266d304");
        owner.setPrivateToken("mHN8exdYzNja1eZkXPxc");

        orgService.updateOrg(org, owner);
    }

    @Test
    public void getOrgDetailTest() throws AlgException {
        OrgVo orgVo = orgService.getOrgDetail("testOrg2");
        System.out.println(JSONObject.toJSONString(orgVo));
    }

    @Test
    public void getOrgListTest() {
        PageRequest pageRequest = new PageRequest(0, 10);
        PageInfo<OrgVo> result = orgService.getOrgList("64bd0e5ee1a6409f97d12c271bb8fa68", pageRequest);
        System.out.println(JSONObject.toJSONString(result));
    }

    @Test
    public void addMemberTest() throws AlgException {
        AlgUser owner = new AlgUser();
        owner.setUsrSn("64bd0e5ee1a6409f97d12c271bb8fa68");
        owner.setUsrCode("gaohaijun");
        owner.setPasswd("47c668187ec1fc010443a9372266d304");
        owner.setPrivateToken("mHN8exdYzNja1eZkXPxc");

        orgService.addMember("1ab380d8078d414f8edc4dcc33a65348","64bd0e5ee1a6409f97d12c271bb8fa68", owner);
    }

    @Test
    public void removeMemberTest() throws AlgException {
        AlgUser owner = new AlgUser();
        owner.setUsrSn("64bd0e5ee1a6409f97d12c271bb8fa68");
        owner.setUsrCode("gaohaijun");
        owner.setPasswd("47c668187ec1fc010443a9372266d304");
        owner.setPrivateToken("mHN8exdYzNja1eZkXPxc");

        orgService.removeMember("1ab380d8078d414f8edc4dcc33a65348","64bd0e5ee1a6409f97d12c271bb8fa68", owner);
    }

    @Test
    public void ownOrganizeTest() throws AlgException {
        List<AlgUser> algUsers = userService.ownOrganize("gaohaijun");
        System.out.println(JSONObject.toJSONString(algUsers));
    }
}
