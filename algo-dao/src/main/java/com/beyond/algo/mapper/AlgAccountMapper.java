package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgAccount;
import java.util.List;

public interface AlgAccountMapper {
    int deleteByPrimaryKey(String accSn);

    int insert(AlgAccount record);

    AlgAccount selectByPrimaryKey(String accSn);

    List<AlgAccount> selectAll();

    int updateByPrimaryKey(AlgAccount record);
}