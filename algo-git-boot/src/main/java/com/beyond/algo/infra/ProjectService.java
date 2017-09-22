package com.beyond.algo.infra;

import java.io.IOException;

public interface ProjectService {

    /**
     * 初始化算法工程.
     */
    void initProject(String username, String projectName) throws Exception;
}
