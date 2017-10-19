package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.algoconsoleboot.model.GitUser;

public interface BuildAntProjectService {
     void buildAndUpLoadProject(GitUser gitUser) throws Exception;
}

