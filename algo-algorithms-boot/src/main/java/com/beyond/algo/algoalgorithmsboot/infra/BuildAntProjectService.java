package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.algoalgorithmsboot.model.GitUser;

public interface BuildAntProjectService {
     void buildAndUpLoadProject(GitUser gitUser) throws Exception;
}

