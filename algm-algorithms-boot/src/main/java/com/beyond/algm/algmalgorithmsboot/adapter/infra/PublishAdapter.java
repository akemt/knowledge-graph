package com.beyond.algm.algmalgorithmsboot.adapter.infra;

import com.beyond.algm.algmalgorithmsboot.model.PublishConfigModel;
import com.beyond.algm.exception.AlgException;

/**
 * @Author: gaohaijun
 * @Description:
 * @Date: 2017/11/23
 */
public interface PublishAdapter {

    /**
     *
     * @param userCode
     * @param projectName
     * @param projectDescription
     * @param algoVersion
     * @throws AlgException
     */
    void initBootProject(String userCode, String projectName, String projectDescription, String algoVersion, PublishConfigModel publishConfigModel, String active,String publishPath) throws AlgException;
}
