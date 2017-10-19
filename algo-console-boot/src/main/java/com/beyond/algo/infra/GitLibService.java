package com.beyond.algo.infra;

import com.beyond.algo.model.GitUser;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;

public interface GitLibService {

    GitlabUser addGitLibUser(GitUser gitUser) throws Exception;

    GitlabProject createGitLibProject(GitUser gitUser) throws Exception;

    /**
     * 在gitlab中创建组织
     *
     * @param orgCode        组织编码
     * @param orgName        组织名称
     * @param createUserName 创建人gitlab用户名
     * @param password       创建人密码
     */
    GitlabGroup createGitLibGroup(String orgCode, String orgName, String createUserName, String password) throws Exception;

    /**
     * 删除组织
     *
     * @param orgCode 组织编码
     */
    void deleteGitLibGroup(String orgCode) throws Exception;
}
