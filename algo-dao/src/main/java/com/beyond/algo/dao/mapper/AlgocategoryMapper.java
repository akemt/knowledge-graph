package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.Algocategory;
import java.util.List;

public interface AlgocategoryMapper {
    int deleteByPrimaryKey(String catsn);

    int insert(Algocategory record);

    Algocategory selectByPrimaryKey(String catsn);

    List<Algocategory> selectAll();

    int updateByPrimaryKey(Algocategory record);
}