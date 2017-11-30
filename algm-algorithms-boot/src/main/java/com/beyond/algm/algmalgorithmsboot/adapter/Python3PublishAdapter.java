package com.beyond.algm.algmalgorithmsboot.adapter;

import com.beyond.algm.algmalgorithmsboot.adapter.infra.PublishAdapter;
import com.beyond.algm.algmalgorithmsboot.model.ProjectConfigModel;
import com.beyond.algm.algmalgorithmsboot.model.PublishConfigModel;
import com.beyond.algm.algmalgorithmsboot.util.FreemarkerUtil;
import com.beyond.algm.common.FileUtil;
import com.beyond.algm.exception.AlgException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gaohaijun
 * @Description:
 * @Date: create in
 */
@Slf4j
public class Python3PublishAdapter implements PublishAdapter {

    @Override
    public void initBootProject(String userCode, String projectName, String projectDescription, String algoVersion, PublishConfigModel publishConfigModel, String active,String publishPath) throws AlgException {
        //TODO:Python3 Spring Boot发布实现
    }
}
