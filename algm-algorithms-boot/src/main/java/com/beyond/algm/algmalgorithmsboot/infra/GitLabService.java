package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import org.gitlab.api.models.*;

public interface GitLabService {

    GitlabUser addGitLabUser(GitUser gitUser) throws Exception;

    GitlabProject createGitLabProject(GitUser gitUser) throws Exception;

    /**
     * 在gitlab中创建组织
     *
     * @param orgCode        组织编码
     * @param orgName        组织名称
     * @param createUserCode 创建人gitlab用户名
     * @param password       创建人密码
     */
    GitlabGroup createGitLabGroup(String orgCode, String orgName, String createUserCode, String password) throws Exception;

    /**
     * 在gitlab中删除组织
     *
     * @param orgCode 组织编码
     */
    void deleteGitLabGroup(String orgCode) throws Exception;

    /**
     * 在gitlab中更新组织
     *
     * @param orgCode        组织编码
     * @param orgName        组织名称
     */
    GitlabGroup updateGitLabGroup(String orgCode, String orgName) throws Exception;

    /**
     * 为组织添加新成员
     *
     * @param orgCode  组织编码
     * @param userCode 用户编码
     */
    GitlabGroupMember addGroupMember(String orgCode, String userCode) throws Exception;

    /**
     * 删除组织成员
     *
     * @param orgCode  组织编码
     * @param userCode 用户编码
     */
    void deleteGroupMember(String orgCode, String userCode) throws Exception;

    /**
     * 通过用户ID删除git用户信息
     *
     * @param id
     * @throws Exception
     */
    void deleteUserByGitUserId(Integer id) throws Exception;

}
