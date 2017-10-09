package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgStar;
import java.util.List;

public interface AlgStarMapper {
    int deleteByPrimaryKey(String starSn);

    int insert(AlgStar record);

    AlgStar selectByPrimaryKey(String starSn);

    List<AlgStar> selectAll();

    int updateByPrimaryKey(AlgStar record);
}