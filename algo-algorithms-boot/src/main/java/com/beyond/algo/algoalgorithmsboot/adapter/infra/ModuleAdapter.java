package com.beyond.algo.algoalgorithmsboot.adapter.infra;

import com.beyond.algo.algoalgorithmsboot.model.GitConfigModel;
import com.beyond.algo.algoalgorithmsboot.model.ProjectConfigModel;
import com.beyond.algo.exception.AlgException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in
 */
public interface ModuleAdapter {
    void createModule(String username, String projectName, GitConfigModel gitConfigModel, ProjectConfigModel projectConfigModel) throws AlgException;
    boolean moduleAntBuild(String  path)throws AlgException;
}
