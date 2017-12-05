package com.beyond.algm.algmcallboot.repository;

import com.beyond.algm.algmcallboot.model.AlgModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgModuleRepository extends JpaRepository<AlgModule,String> {
    @Query("select t from AlgModule t where t.usrSn = :usrSn and t.modId = :modId")
    AlgModule findByModSn(@Param("usrSn")String usrSn,@Param("modId") String modId);
}
