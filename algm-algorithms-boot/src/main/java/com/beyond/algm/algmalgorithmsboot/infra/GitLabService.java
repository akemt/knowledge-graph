package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabGroupMember;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;

public interface GitLabService {

    /**
     * 实现gitlab上创建用户
     *
     * @param gitUser
     * @return
     * @throws Exception
     * @author xialf
     */
    GitlabUser addGitLabUser(GitUser gitUser) throws Exception;

    /**
     * 在gitlab上创建项目
     *
     * @param gitUser
     * @return
     * @throws Exception
     * @author xialf
     */
    GitlabProject createGitLabProject(GitUser gitUser) throws Exception;

    /**
     * 在gitlab中创建组织
     *
     * @param orgCode      组织编码
     * @param orgName      组织名称
     * @param privateToken 创建者秘钥
     * @author xialf
     */
    GitlabGroup createGitLabGroup(String orgCode, String orgName, String privateToken) throws Exception;

    /**
     * 在gitlab中删除组织
     *
     * @param orgCode      组织编码
     * @param privateToken 创建者秘钥
     */
    void deleteGitLabGroup(String orgCode, String privateToken) throws Exception;

    /**
     * 在gitlab中更新组织
     *
     * @param orgCode      组织编码
     * @param orgName      组织名称
     * @param privateToken 创建者秘钥
     * @author xialf
     */
    GitlabGroup updateGitLabGroup(String orgCode, String orgName, String privateToken) throws Exception;

    /**
     * 为组织添加新成员
     *
     * @param orgCode            组织编码
     * @param userCode           用户编码
     * @param ownerPrivateToken  创建者秘钥
     * @param memberPrivateToken 成员秘钥
     */
    GitlabGroupMember addGroupMember(String orgCode, String userCode, String ownerPrivateToken, String memberPrivateToken) throws Exception;

    /**
     * 删除组织成员
     *
     * @param orgCode            组织编码
     * @param userCode           用户编码
     * @param ownerPrivateToken  创建者秘钥
     * @param memberPrivateToken 成员秘钥
     */
    void deleteGroupMember(String orgCode, String userCode, String ownerPrivateToken, String memberPrivateToken) throws Exception;

    /**
     * 通过用户ID删除git用户信息
     *
     * @param id
     * @throws Exception
     */
    void deleteUserByGitUserId(Integer id) throws Exception;

}
