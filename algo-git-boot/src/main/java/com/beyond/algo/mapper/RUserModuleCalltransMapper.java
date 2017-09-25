package com.beyond.algo.mapper;

import com.beyond.algo.model.RUserModuleCalltrans;
import java.util.List;

public interface RUserModuleCalltransMapper {
    int deleteByPrimaryKey(String umcsn);

    int insert(RUserModuleCalltrans record);

    RUserModuleCalltrans selectByPrimaryKey(String umcsn);

    List<RUserModuleCalltrans> selectAll();

    int updateByPrimaryKey(RUserModuleCalltrans record);
}