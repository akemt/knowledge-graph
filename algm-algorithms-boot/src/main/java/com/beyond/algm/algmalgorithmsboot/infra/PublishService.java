package com.beyond.algm.algmalgorithmsboot.infra;

public interface PublishService {

    /**
     * 初始化Spring Boot工程.
     */
    void initBootProject(String lanSn, String userCode, String projectName, String projectDescription, String algoVersion) throws Exception;
}
