package com.beyond.algo.mapper;

import com.beyond.algo.model.License;
import java.util.List;

public interface LicenseMapper {
    int deleteByPrimaryKey(String licsn);

    int insert(License record);

    License selectByPrimaryKey(String licsn);

    List<License> selectAll();

    int updateByPrimaryKey(License record);
}