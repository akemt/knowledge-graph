package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.algmalgorithmsboot.model.GitUser;

public interface BuildAntProjectService {
     void buildAndUpLoadProject(GitUser gitUser) throws Exception;
}

