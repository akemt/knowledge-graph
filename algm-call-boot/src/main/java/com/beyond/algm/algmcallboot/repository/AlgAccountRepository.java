package com.beyond.algm.algmcallboot.repository;

import com.beyond.algm.algmcallboot.model.AlgAccount;
import com.beyond.algm.algmcallboot.model.AlgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AlgAccountRepository extends JpaRepository<AlgAccount,String> {
    @Query("select t from AlgAccount t where t.usrSn = ?1")
    AlgAccount findByUsrSn(String usrSn);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update alg_account as c set c.cash_bal = ?1 where c.usr_sn = ?2",nativeQuery = true)
    int updateUsrSnByCashBal(float cash_bal,String usr_sn);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update alg_account as c set c.free_bal = ?1 where c.usr_sn = ?2",nativeQuery = true)
    int updateUsrSnByFreeBal(float free_bal,String usr_sn);
}
