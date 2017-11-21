package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgAuthCodeDomain;
import java.util.List;

public interface AlgAuthCodeDomainMapper {
    int deleteByPrimaryKey(String addSn);

    int deleteByAcdSn(String acdSn);

    int insert(AlgAuthCodeDomain record);

    AlgAuthCodeDomain selectByPrimaryKey(String addSn);

    List<AlgAuthCodeDomain> selectAll();

    List<AlgAuthCodeDomain> listAcdSnUrl(String acdSn);

    int updateByPrimaryKey(AlgAuthCodeDomain record);
}