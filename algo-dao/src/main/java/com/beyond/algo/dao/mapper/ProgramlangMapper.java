package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.Programlang;
import java.util.List;

public interface ProgramlangMapper {
    int deleteByPrimaryKey(String lansn);

    int insert(Programlang record);

    Programlang selectByPrimaryKey(String lansn);

    List<Programlang> selectAll();

    int updateByPrimaryKey(Programlang record);
}