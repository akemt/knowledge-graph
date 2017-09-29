package com.beyond.algo.mapper;

import com.beyond.algo.model.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String usrsn);

    int insert(User record);

    User selectByPrimaryKey(String usrsn);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectUsrname(String usrsn);
}