package com.beyond.algm.algmcallboot.repository;

import com.beyond.algm.algmcallboot.model.AlgAuthCode;
import com.beyond.algm.algmcallboot.model.AlgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgAuthCodeRepository extends JpaRepository<AlgAuthCode,String> {
    AlgAuthCode findByAcdSn(String acdSn);
}
