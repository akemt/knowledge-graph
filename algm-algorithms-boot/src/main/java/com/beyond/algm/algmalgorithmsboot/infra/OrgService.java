package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.OrgVo;

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
     *
     * @param orgSn 组织串号
     * @return 是否删除成功
     */
    void deleteOrg(String orgSn) throws AlgException;

    /**
     * 获取组织详情
     *
     * @param orgSn
     * @return
     * @throws AlgException
     */
    OrgVo getOrgDetail(String orgSn) throws AlgException;
}
