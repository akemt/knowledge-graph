package com.beyond.algo.algoconsoleboot.ModuleAdapter.infra;

import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigModel;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in
 */
public interface CreateModuleAdapter {
    void createModule(String username, String projectName, GitConfigModel gitConfigModel, ProjectConfigModel projectConfigModel) throws Exception;
}
