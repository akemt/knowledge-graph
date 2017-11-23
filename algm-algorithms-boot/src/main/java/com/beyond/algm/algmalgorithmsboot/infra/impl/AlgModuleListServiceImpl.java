package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.AlgModuleListService;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.mapper.AlgArticleListMapper;
import com.beyond.algm.mapper.AlgDataMapper;
import com.beyond.algm.mapper.AlgModuleMapper;
import com.beyond.algm.mapper.AlgModuleUsageMapper;
import com.beyond.algm.model.AlgArticleList;
import com.beyond.algm.vo.AlgDifDataListVo;
import com.beyond.algm.vo.AlgModuleListVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 1:16
 */
@Service
public class AlgModuleListServiceImpl implements AlgModuleListService {
    @Autowired
    AlgModuleMapper algModuleMapper;
    @Autowired
    AlgModuleUsageMapper algModuleUsageMapper;
    @Autowired
    AlgArticleListMapper algArticleListMapper;
    @Autowired
    AlgDataMapper algDataMapper;

    @Override
    public List<AlgModuleListVo> findModuleList(String catSn, String usage ,String modName,Integer numPage,Integer numRows,String id,String usrCode) throws AlgException {
        //初步设定用数据库进行排序查询
        PageHelper.startPage(numPage,numRows);
        List<AlgModuleListVo> resultAlgModule = algModuleMapper.findModuleList(catSn,usage,modName,id,usrCode);
        return resultAlgModule;
    }

    @Override
    //为不同实现功能获取文献信息
    public AlgArticleList findAlgArticleList(Integer id) throws AlgException{
        AlgArticleList algArticleList = algArticleListMapper.selectByPrimaryKey(id);
        return algArticleList;
    }

    //我的收藏
    @Override
    public List<AlgModuleListVo> findModuleCollect(String catSn, String usage ,String modName,Integer numPage,Integer numRows, String usrSn) throws AlgException {
        //初步设定用数据库进行排序查询
        PageHelper.startPage(numPage,numRows);
        List<AlgModuleListVo> resultAlgModule = algModuleMapper.findModuleCollect(catSn,usage,modName,usrSn);
        return resultAlgModule;
    }

    //不同是实现- 数据列表
    @Override
    public List<AlgDifDataListVo> findDifDataList(Integer id) throws AlgException {
        List<AlgDifDataListVo> resultAlgModule = algDataMapper.findDifDataList(id);
        return resultAlgModule;
    }
}
