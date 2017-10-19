package com.beyond.algo.infra.impl;

import com.beyond.algo.infra.AlgorithmCollectAndRankService;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgStarMapper;
import com.beyond.algo.vo.CollectArticlesVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:算法收藏
 * @date ：11:25 2017/10/17
 */
@Service
public class AlgorithmCollectAndRankServiceImpl  implements AlgorithmCollectAndRankService {
    @Autowired
    private AlgStarMapper algStarMapper;

    @Autowired
    private AlgModuleMapper algModuleMapper;

    @Override
    public List<CollectArticlesVo> collectArticles(CollectArticlesVo collectArticlesVo){
        //分页处理
        PageHelper.startPage(collectArticlesVo.getPage(), collectArticlesVo.getRows());
        List<CollectArticlesVo>collectArticlesVoList=algStarMapper.selectArticles(collectArticlesVo);
        return collectArticlesVoList;
    }
    @Override
    public List getRankList(){
        List<String> abc=new ArrayList<String>();
        abc.add("1");
        abc.add("2");
        abc.add("3");
        abc.add("4");
        abc.add("5");
        abc.add("6");
        List bcd=  algModuleMapper.getRankList(abc);
        return bcd;
    }
}
