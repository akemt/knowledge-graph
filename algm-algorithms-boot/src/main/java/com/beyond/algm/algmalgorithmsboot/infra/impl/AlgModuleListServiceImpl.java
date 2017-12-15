package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.AlgModuleListService;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.*;
import com.beyond.algm.model.AlgArticleList;
import com.beyond.algm.model.AlgStar;
import com.beyond.algm.vo.AlgDifDataListVo;
import com.beyond.algm.vo.AlgModuleListVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 1:16
 */
@Service
@Slf4j
public class AlgModuleListServiceImpl implements AlgModuleListService {
    @Autowired
    AlgModuleMapper algModuleMapper;
    @Autowired
    AlgModuleUsageMapper algModuleUsageMapper;
    @Autowired
    AlgArticleListMapper algArticleListMapper;
    @Autowired
    AlgDataMapper algDataMapper;
    @Autowired
    AlgStarMapper algStarMapper;

    @Override
    public List<AlgModuleListVo> findModuleList(String catSn, String usage ,String modName,Integer numPage,Integer numRows,String id,String usrCode) throws AlgException {
        //初步设定用数据库进行排序查询
        PageHelper.startPage(numPage,numRows);
        List<AlgModuleListVo> resultAlgModule = algModuleMapper.findModuleList(catSn,usage,modName,id,usrCode);
        return resultAlgModule;
    }
    //分页的
    @Override
    public PageInfo<AlgModuleListVo> findModulePage(String catSn, String usage , String modName, PageInfo pageInfo, String id, String usrCode) throws AlgException {
        //初步设定用数据库进行排序查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        Page<AlgModuleListVo> resultAlgModule = algModuleMapper.findModulePage(catSn,usage,modName,id,usrCode);
        return new PageInfo<>(resultAlgModule);
    }

    //用户是否收藏了算法标记：star_sn
    @Override
    public PageInfo<AlgModuleListVo> findModuleByStar(String catSn, String usage , String modName, PageInfo pageInfo, String id, String usrCode) throws AlgException {
        //初步设定用数据库进行排序查询
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        Page<AlgModuleListVo> resultAlgModule = algModuleMapper.findModuleByStar(catSn,usage,modName,id,usrCode);
        return new PageInfo<>(resultAlgModule);
    }

    @Override
    //为不同实现功能获取文献信息
    public AlgArticleList findAlgArticleList(Integer id) throws AlgException{
        AlgArticleList algArticleList = algArticleListMapper.selectByPrimaryKey(id);
        return algArticleList;
    }

    //我的收藏
    @Override
    public Page<AlgModuleListVo> findModuleCollect(String catSn, String usage ,String modName,Integer pageNum,Integer pageSize, String usrSn) throws AlgException {
        //初步设定用数据库进行排序查询
        PageHelper.startPage(pageNum,pageSize);
        Page<AlgModuleListVo> resultAlgModule = algModuleMapper.findModuleCollect(catSn,usage,modName,usrSn);
        return resultAlgModule;
    }

    //不同是实现- 数据列表
    @Override
    public List<AlgDifDataListVo> findDifDataList(Integer id) throws AlgException {
        List<AlgDifDataListVo> resultAlgModule = algDataMapper.findDifDataList(id);
        return resultAlgModule;
    }

    //收藏算法
    @Override
    public int modStar(String modSn,String usrSn) throws AlgException{
        AlgStar algStar = new AlgStar();
        algStar.setStarSn(UUIDUtil.createUUID());
        algStar.setModSn(modSn);
        algStar.setUsrSn(usrSn);
        algStar.setCreatTime(new Date());
        try {
            int del = algStarMapper.deleteAlgStar(algStar);
            if (del <= 0){
                algStarMapper.insert(algStar);
                del = 0;
            }
            return del;
        } catch (Exception e) {
            log.error("充值失败。收藏串号：{},模块串号：{},用户串号：{}",algStar.getStarSn(),algStar.getModSn(),
                    algStar.getUsrSn(),e);
            throw new AlgException("BEYOND.ALG.MODSTAR.INSERT.0000002",new String[]{});
        }
    }
}
