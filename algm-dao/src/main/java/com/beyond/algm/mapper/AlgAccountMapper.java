package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgAccount;
import java.util.List;

public interface AlgAccountMapper {
    int deleteByPrimaryKey(String accSn);

    int insert(AlgAccount record);

    AlgAccount selectByPrimaryKey(String accSn);

    List<AlgAccount> selectAll();

    int updateByPrimaryKey(AlgAccount record);

    AlgAccount selectAccount(String usrSn);

    int presentCash(String usrSn);

    int updateByFreeBal(Float freeBal);
}