package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgModule;
import com.beyond.algo.vo.AlgModuleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlgModuleMapper {
    int deleteByPrimaryKey(String modSn);

    int insert(AlgModule record);

    AlgModule selectByPrimaryKey(String modSn);

    List<AlgModule> selectAll();

    int updateByPrimaryKey(AlgModule record);

    List<AlgModule> getRankList(List rankList);

    List<AlgModule> listAlgByUsage(String catName, String usage);

    /*List<AlgModule> listAlgByUsageStar(String catName);

    List<AlgModule> listAlgByUsageFollow(String catName);

    List<AlgModule> listAlgByUsageCall(String catName);

    List<AlgModule> listAlgByUsageCredit(String catName);*/

    AlgModule selectByUsrSnAndModId(@Param("usrSn") String usrSn, @Param("modId")String modId);

    AlgModuleVo getAlgorithmDetail(String modSn);
}