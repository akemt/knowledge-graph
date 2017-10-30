package com.beyond.algo.algoconsoleboot.adapter;

import com.beyond.algo.algoconsoleboot.adapter.infra.Adapter;
import com.beyond.algo.algoconsoleboot.model.GitConfigModel;
import com.beyond.algo.algoconsoleboot.model.ProjectConfigModel;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in
 */
@Configuration
public class CreatePython3Module implements Adapter {
    @Override
    public void createModule(String username, String projectName, GitConfigModel gitConfigModel, ProjectConfigModel projectConfigModel) throws Exception {
        System.out.println("进入创建Python工程的页面");
    }
}
