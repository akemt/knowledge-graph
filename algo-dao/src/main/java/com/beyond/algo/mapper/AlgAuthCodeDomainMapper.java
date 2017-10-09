package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgAuthCodeDomain;
import java.util.List;

public interface AlgAuthCodeDomainMapper {
    int deleteByPrimaryKey(String addSn);

    int insert(AlgAuthCodeDomain record);

    AlgAuthCodeDomain selectByPrimaryKey(String addSn);

    List<AlgAuthCodeDomain> selectAll();

    int updateByPrimaryKey(AlgAuthCodeDomain record);
}