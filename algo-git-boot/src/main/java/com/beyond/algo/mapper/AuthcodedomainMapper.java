package com.beyond.algo.mapper;

import com.beyond.algo.model.Authcodedomain;
import java.util.List;

public interface AuthcodedomainMapper {
    int deleteByPrimaryKey(String addsn);

    int insert(Authcodedomain record);

    Authcodedomain selectByPrimaryKey(String addsn);

    List<Authcodedomain> selectAll();

    int updateByPrimaryKey(Authcodedomain record);
}