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
    void createModule(String username, String projectName, GitConfigModel gitConfigModel, ProjectConfigModel projectConfigModel) throws AlgException;
    boolean moduleAntBuild(String  path)throws AlgException,Exception;
}
