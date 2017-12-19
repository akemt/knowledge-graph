package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.AlgModuleListService;
import com.beyond.algm.common.UUIDUtil;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.*;
import com.beyond.algm.model.AlgArticleList;
import com.beyond.algm.model.AlgModuleUsage;
import com.beyond.algm.model.AlgStar;
import com.beyond.algm.vo.AlgDifDataListVo;
import com.beyond.algm.vo.AlgModuleListVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = AlgException.class)
    public Long modStar(String modSn,String usrSn) throws AlgException{
        //查取该算法目前是否有人收藏
        Long starCntCount = algModuleUsageMapper.selectStarCntCount(modSn);
        //查取该算法目前收藏数
        Long starCnt = algModuleUsageMapper.selectStarCnt(modSn);
        log.info("该算法目前人收藏是否有人收藏:{}",starCnt);

        //创建收藏统计表（alg_module_usage）对象
        AlgModuleUsage algModuleUsage = new AlgModuleUsage();
        algModuleUsage.setModSn(modSn);

        //创建收藏对象，并且赋值
        AlgStar algStar = new AlgStar();
        algStar.setModSn(modSn);
        algStar.setUsrSn(usrSn);
        //查询该用户是否收藏该算法
        int count = algStarMapper.selectStarCnt(algStar);
        log.info("该用户是否收藏该算法:{}",count);

        //该用户收藏过
        if(count>0){
            //取消收藏
            int del = algStarMapper.deleteAlgStar(algStar);
            log.info("取消收藏");
            //收藏统计表（alg_module_usage）重新更新收藏总数。
            if(starCntCount>0){
                algModuleUsage.setStarCnt(--starCnt);
                algModuleUsageMapper.updateByModSn(algModuleUsage);
                log.info("取消收藏，重新更新收藏总数:{}",starCnt);
            }
        }else { //该用户没有收藏过
            //该用户表收藏该算法
            algStar.setStarSn(UUIDUtil.createUUID());
            algStar.setCreatTime(new Date());
            algStarMapper.insert(algStar);
            log.info("该用户表收藏该算法");
            //更新收藏统计表（alg_module_usage）该算法的收藏总数。
            if(starCntCount>0){
                //该算法有用户收藏过，更新收藏总数
                algModuleUsage.setStarCnt(++starCnt);
                algModuleUsageMapper.updateByModSn(algModuleUsage);
                log.info("该算法有用户收藏过，更新收藏总数:{}",starCnt);
            }else {
                //该算法无用户收藏过，新插入一条。
                algModuleUsage.setUsgSn(UUIDUtil.createUUID());
                algModuleUsage.setStarCnt(++starCnt);
                algModuleUsageMapper.insert(algModuleUsage);
                log.info("该算法无用户收藏过，新插入一条:{}",starCnt);
            }
        }
        return starCnt;
    }
}
