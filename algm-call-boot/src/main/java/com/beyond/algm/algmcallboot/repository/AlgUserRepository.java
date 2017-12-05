package com.beyond.algm.algmcallboot.repository;

import com.beyond.algm.algmcallboot.model.AlgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgUserRepository extends JpaRepository<AlgUser,String> {
    @Query("select t from AlgUser t where t.usrCode = ?1")
    AlgUser findByUsrCode(String usrCode);
}
