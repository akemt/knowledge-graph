package com.beyond.algm.algmalgorithmsboot.adapter.infra;

import com.beyond.algm.algmalgorithmsboot.model.GitConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigModel;
import com.beyond.algm.exception.AlgException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in
 */
public interface ModuleAdapter {
    /**
     *
     * @param username 创建用户名称
     * @param projectName 项目Id
     * @param gitConfigModel git基础配置
     * @param projectConfigModel 项目基础配置
     * @param active 调试器开关
     * @throws AlgException
     */
    void createModule(String username, String projectName, GitConfigModel gitConfigModel, ProjectConfigModel projectConfigModel,String active) throws AlgException;

    /**
     * 项目编译
     * @param path java 的path为 bulid.xml
     * @return
     * @throws AlgException
     * @throws Exception
     */
    boolean moduleAntBuild(String  path)throws AlgException;
}
