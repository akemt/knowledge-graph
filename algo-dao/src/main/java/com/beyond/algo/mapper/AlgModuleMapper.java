package com.beyond.algo.mapper;

import com.beyond.algo.model.AlgModule;
import com.beyond.algo.vo.AlgModuleListVo;
import com.beyond.algo.vo.AlgModuleVo;
import com.beyond.algo.vo.AlgorithmDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlgModuleMapper {
    int deleteByPrimaryKey(String modSn);

    int insert(AlgModule record);

    AlgModule selectByPrimaryKey(String modSn);

    List<AlgModule> selectAll();

    int updateByPrimaryKey(AlgModule record);

    List<AlgModule> getRankList(List rankList);

    /**
     *  分页查询算法列表
     * @param catName
     * @param usage
     * @param modName
     * @return
     */
    List<AlgModuleListVo> findModuleList(String catName, String usage, String modName,String id,String usrCode);

    AlgModule selectByUsrSnAndModId(@Param("usrSn") String usrSn, @Param("modId")String modId);

    AlgModuleVo getAlgorithmDetail(AlgorithmDetailVo algorithmDetailVo);

    /**
     *  我的收藏列表
     */
    List<AlgModuleListVo> findModuleCollect(String catName, String usage, String modName,String usrSn);
}