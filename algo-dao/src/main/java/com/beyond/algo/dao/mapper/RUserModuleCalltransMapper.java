package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.RUserModuleCalltrans;
import java.util.List;

public interface RUserModuleCalltransMapper {
    int deleteByPrimaryKey(String umcsn);

    int insert(RUserModuleCalltrans record);

    RUserModuleCalltrans selectByPrimaryKey(String umcsn);

    List<RUserModuleCalltrans> selectAll();

    int updateByPrimaryKey(RUserModuleCalltrans record);
}