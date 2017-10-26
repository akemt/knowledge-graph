package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgUser;
import java.util.List;

public interface AlgUserMapper {
    int deleteByPrimaryKey(String usrSn);

    int insert(AlgUser record);

    AlgUser selectByPrimaryKey(String usrSn);

    List<AlgUser> selectAll();

    int updateByPrimaryKey(AlgUser record);

    AlgUser selectUsrname(String usrsn);

    int update(AlgUser record);

    // 根据组织编码获取组织个数（用于新建组织时判断组织编码唯一性）
    int countOrgByCode(String orgCode);

    AlgUser selectUsrCode(String usrCode);


}