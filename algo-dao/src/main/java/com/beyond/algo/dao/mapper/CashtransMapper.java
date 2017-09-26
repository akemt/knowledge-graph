package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.Cashtrans;
import java.util.List;

public interface CashtransMapper {
    int deleteByPrimaryKey(String trasn);

    int insert(Cashtrans record);

    Cashtrans selectByPrimaryKey(String trasn);

    List<Cashtrans> selectAll();

    int updateByPrimaryKey(Cashtrans record);
}