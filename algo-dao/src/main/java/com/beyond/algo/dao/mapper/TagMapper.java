package com.beyond.algo.dao.mapper;

import com.beyond.algo.dao.model.Tag;
import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(String tagsn);

    int insert(Tag record);

    Tag selectByPrimaryKey(String tagsn);

    List<Tag> selectAll();

    int updateByPrimaryKey(Tag record);
}