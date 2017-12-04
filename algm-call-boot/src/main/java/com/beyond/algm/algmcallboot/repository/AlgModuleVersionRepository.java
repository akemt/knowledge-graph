package com.beyond.algm.algmcallboot.repository;

import com.beyond.algm.algmcallboot.model.AlgModuleVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgModuleVersionRepository extends JpaRepository<AlgModuleVersion,Long> {
    @Query("select count(t.verSn) from AlgModuleVersion t where t.modSn = :modSn and t.ver_code_l1 = :ver_code_l1" +
            " and t.ver_code_l2 = :ver_code_l2 and t.ver_code_l3 = :ver_code_l3")
    public Long findByVerSnCount(@Param("modSn")String modSn, @Param("ver_code_l1") Integer ver_code_l1,
                                 @Param("ver_code_l2") Integer ver_code_l2, @Param("ver_code_l3") Integer ver_code_l3);

    @Query("select t.verLoyaltyFee from AlgModuleVersion t where t.modSn = :modSn " +
            "and t.ver_code_l1 = :ver_code_l1 and t.ver_code_l2 = :ver_code_l2 and t.ver_code_l3 = :ver_code_l3")
    public Float verLoyaltyFee(@Param("modSn")String modSn, @Param("ver_code_l1") Integer ver_code_l1,
                            @Param("ver_code_l2") Integer ver_code_l2, @Param("ver_code_l3") Integer ver_code_l3);
}
