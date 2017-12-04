package com.beyond.algm.algmcallboot.repository;

import com.beyond.algm.algmcallboot.model.AlgAuthCode;
import com.beyond.algm.algmcallboot.model.AlgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlgAuthCodeRepository extends JpaRepository<AlgAuthCode,String> {
    @Query("select t.usrSn as usrSn from AlgAuthCode t where t.acdId = :acdId")
    String findByAcdId(@Param("acdId")String acdId);
}
