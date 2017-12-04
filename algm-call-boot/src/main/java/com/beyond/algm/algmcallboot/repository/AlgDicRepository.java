package com.beyond.algm.algmcallboot.repository;

import com.beyond.algm.algmcallboot.model.AlgDic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgDicRepository extends JpaRepository<AlgDic,String> {
    @Query("select t.dicValue from AlgDic t where t.dicCode = :dicCode and t.dicKey = :dicKey")
    public String findByDicSn(@Param("dicCode")String dicCode, @Param("dicKey") String dicKey);
}
