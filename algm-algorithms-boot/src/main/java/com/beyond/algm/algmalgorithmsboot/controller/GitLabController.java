package com.beyond.algm.algmalgorithmsboot.controller;

import com.beyond.algm.algmalgorithmsboot.infra.GitLabService;
import com.beyond.algm.algmalgorithmsboot.infra.JGitService;
import com.beyond.algm.algmalgorithmsboot.model.GitUser;
import com.beyond.algm.common.Result;
import com.beyond.algm.common.ResultEnum;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author ：zhangchuanzhi
 * @Description:gitlab操作
 * @date ：8:51 2017/9/28
 */
@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/git")
public class GitLabController {
    @Autowired
    private GitLabService gitLabService;
    @Autowired
    private JGitService jGitService;

    /**
     * @author ：zhangchuanzhi
     * @Description:实现gitlab上创建用户
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：9:00 2017/9/28
     */
    @RequestMapping(value = "/addGitLabUser", method = RequestMethod.POST)
    public @ResponseBody
    Result addGitLabUser(GitUser gitUser) {
        log.info("用户名：{}用户全称：{} 用户密码：{} 用户邮箱：{}", gitUser.getUsrCode(), gitUser.getFullName(), gitUser.getPassword(), gitUser.getEmail());
        try {
            GitlabUser gitlabUser = gitLabService.addGitLabUser(gitUser);
            if (gitlabUser != null) {
                return Result.successResponse();
            }
            return Result.failureResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:在gitlab上创建项目
     * @param：GitUser
     * @Modify By :zhangchuanzhi
     * @date ：9:31 2017/9/28
     */
    @RequestMapping(value = "/createProject", method = RequestMethod.POST)
    public @ResponseBody
    Result createGitLabProject(GitUser gitUser) {
        log.info("用户名：{} 用户密码：{} 项目名称：{}", gitUser.getUsrCode(), gitUser.getPassword(), gitUser.getProjectName());
        try {
            GitlabProject gitlabProject = gitLabService.createGitLabProject(gitUser);
            if (gitlabProject != null) {
                return Result.successResponse();
            }
            return Result.failureResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:从git首次克隆项目
     * @param：GitUser
     * @Modify By :zhangchuanzhi
     * @date ：9:43 2017/9/28
     */
    @RequestMapping(value = "/cloneProject", method = RequestMethod.POST)
    public @ResponseBody
    Result gitCloneProject(GitUser gitUser) {
        log.info("gitCloneProject方法用户名：{} 用户密码{} 项目名称：{}", gitUser.getUsrCode(), gitUser.getPassword(), gitUser.getProjectName());
        try {
            jGitService.gitCloneProject(gitUser);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.successResponse();

    }

    /**
     * @param :GitUser
     * @return
     * @author ：zhangchuanzhi
     * @Description:初始化时提交本地所有代码到远端仓库
     */
    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    public @ResponseBody
    Result initCommitAndPushAllFiles(GitUser gitUser) throws AlgException{
        String version = jGitService.commitAndPushAllFiles(gitUser);
        return Result.successResponse();
    }

    /**
     * @param :gitUser
     * @return
     * @Description:删除本地文件同时同步服务器 author:zhangchuanzhi
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public @ResponseBody
    Result commitAndPushDelAllFiles(GitUser gitUser) {
        try {
            boolean result = jGitService.commitAndPushDelAllFiles(gitUser);
            if (result) {
                return Result.successResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.failureResponse();
    }

    /**
     * 查看文件状态
     * author:zhangchuanzhi
     *
     * @param :gitUser
     * @return
     */
    @RequestMapping(value = "/showStatus", method = RequestMethod.POST)
    public @ResponseBody
    Result gitShowStatus(String path) {
        try {
            File repoDir = new File(path);
            jGitService.gitShowStatus(repoDir);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
        return Result.successResponse();
    }
}
