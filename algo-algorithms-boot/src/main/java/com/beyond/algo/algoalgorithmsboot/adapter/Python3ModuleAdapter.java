package com.beyond.algo.algoalgorithmsboot.adapter;

import com.beyond.algo.algoalgorithmsboot.adapter.infra.ModuleAdapter;
import com.beyond.algo.algoalgorithmsboot.model.GitConfigModel;
import com.beyond.algo.algoalgorithmsboot.model.ProjectConfigModel;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in
 */
@Configuration
public class Python3ModuleAdapter implements ModuleAdapter {
    @Override
    public void createModule(String username, String projectName, GitConfigModel gitConfigModel, ProjectConfigModel projectConfigModel) throws Exception {
        System.out.println("进入创建Python工程的页面");
    }
    @Override
    public boolean moduleAntBuild(String  path)throws Exception{
        return true;

    }
}
