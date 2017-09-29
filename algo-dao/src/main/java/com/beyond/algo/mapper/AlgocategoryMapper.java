package com.beyond.algo.mapper;

import com.beyond.algo.model.Algocategory;
import java.util.List;

public interface AlgocategoryMapper {
    int deleteByPrimaryKey(String catsn);

    int insert(Algocategory record);

    Algocategory selectByPrimaryKey(String catsn);

    List<Algocategory> selectAll();

    int updateByPrimaryKey(Algocategory record);
}