package com.beyond.algo.infra;

import com.beyond.algo.model.GitUser;

public interface BuildAntProjectService {
     void buildAndUpLoadProject(GitUser gitUser) throws Exception;
}

