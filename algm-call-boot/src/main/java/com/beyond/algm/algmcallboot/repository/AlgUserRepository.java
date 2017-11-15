package com.beyond.algm.algmcallboot.repository;

import com.beyond.algm.algmcallboot.model.AlgUserCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlgUserRepository extends JpaRepository<AlgUserCall,Long> {
    List<AlgUserCall> findByCallUsrSn(String callUsrSn);
}
