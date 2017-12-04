package com.beyond.algm.algmcallboot.repository;

import com.beyond.algm.algmcallboot.model.AlgAuthCodeDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlgAuthCodeDomainRepository extends JpaRepository<AlgAuthCodeDomain,String> {
    @Query("select t.addUrl as addUrl from AlgAuthCodeDomain t, AlgAuthCode d " +
            "where t.acdSn = d.acdSn and d.usrSn = :usrSn and d.acdId = :acdId")
    List<String> findByAddSn(@Param("usrSn")String usrSn,@Param("acdId")String acdId);
}
