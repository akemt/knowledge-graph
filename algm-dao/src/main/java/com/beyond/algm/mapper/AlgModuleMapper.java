package com.beyond.algm.mapper;

import com.beyond.algm.model.AlgModule;
import com.beyond.algm.vo.AlgModuleListVo;
import com.beyond.algm.vo.AlgModuleVo;
import com.beyond.algm.vo.AlgorithmDetailVo;
import com.github.pagehelper.Page;
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
     * @param catSn
     * @param usage
     * @param modName
     * @return
     */
    List<AlgModuleListVo> findModuleList(String catSn, String usage, String modName,String id,String usrCode);

    //分页的
    Page<AlgModuleListVo> findModulePage(String catSn, String usage, String modName, String id, String usrCode);

    AlgModule selectByUsrSnAndModId(@Param("usrSn") String usrSn, @Param("modId")String modId);

    AlgModuleVo getAlgorithmDetail(AlgorithmDetailVo algorithmDetailVo);

    /**
     *  我的收藏列表
     */
    Page<AlgModuleListVo> findModuleCollect(String catSn, String usage, String modName,String usrSn);
}