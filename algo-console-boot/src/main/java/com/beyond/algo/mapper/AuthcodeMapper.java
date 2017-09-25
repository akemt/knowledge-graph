package com.beyond.algo.mapper;

import com.beyond.algo.model.Authcode;
import java.util.List;

public interface AuthcodeMapper {
    int deleteByPrimaryKey(String acdsn);

    int insert(Authcode record);

    Authcode selectByPrimaryKey(String acdsn);

    List<Authcode> selectAll();

    int updateByPrimaryKey(Authcode record);
}