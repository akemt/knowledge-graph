package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;

public interface OrgService {

    /**
     * 创建组织
     *
     * @param org 组织信息
     * @return 创建后组织对象
     */
    AlgUser createOrg(AlgUser org, String createUserCode, String password) throws AlgException;

    /**
     * 删除组织
     * @param orgSn 组织串号
     * @return 是否删除成功
     */
    void deleteOrg(String orgSn) throws AlgException;
}
