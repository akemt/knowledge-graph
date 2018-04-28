package com.hiekn.plantdata.mapper;

import com.hiekn.plantdata.Entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    User queryUserInfoByUsrname(String strUsrName);
}
