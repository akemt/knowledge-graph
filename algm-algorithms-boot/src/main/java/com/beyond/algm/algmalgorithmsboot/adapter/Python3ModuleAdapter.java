package com.beyond.algm.algmalgorithmsboot.adapter;

import com.beyond.algm.algmalgorithmsboot.adapter.infra.ModuleAdapter;
import com.beyond.algm.algmalgorithmsboot.model.GitConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigModel;
import com.beyond.algm.exception.AlgException;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in
 */
@Configuration
public class Python3ModuleAdapter implements ModuleAdapter {
    @Override
    public void createModule(String username, String projectName, GitConfigModel gitConfigModel, ProjectConfigModel projectConfigModel) throws AlgException {
        System.out.println("进入创建Python工程的页面");
    }
    @Override
    public boolean moduleAntBuild(String  path)throws AlgException{
        return true;

    }
}
