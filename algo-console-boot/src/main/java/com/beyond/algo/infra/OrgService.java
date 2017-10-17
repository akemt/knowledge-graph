package com.beyond.algo.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;

public interface OrgService {

    /**
     * 创建组织
     *
     * @param org 组织信息
     * @return 是否成功
     */
    AlgUser createOrg(AlgUser org, String createUserCode, String password) throws AlgException;
}
