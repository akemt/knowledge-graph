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
}