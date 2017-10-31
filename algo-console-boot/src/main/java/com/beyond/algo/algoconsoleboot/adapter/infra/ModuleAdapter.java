package com.beyond.algo.algoconsoleboot.adapter.infra;

import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.GitUser;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigModel;
import com.beyond.algo.exception.AlgException;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in
 */
public interface ModuleAdapter {
    void createModule(String username, String projectName, GitConfigModel gitConfigModel, ProjectConfigModel projectConfigModel) throws Exception;
    boolean moduleAntBuild(String  path)throws Exception;
}
