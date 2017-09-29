package com.beyond.algo.mapper;

import com.beyond.algo.model.Account;
import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(String accsn);

    int insert(Account record);

    Account selectByPrimaryKey(String accsn);

    List<Account> selectAll();

    int updateByPrimaryKey(Account record);
}