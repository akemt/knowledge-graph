package com.beyond.algo.algoconsoleboot.infra.impl;

import com.beyond.algo.algoconsoleboot.infra.AlgorithmCollectAndRankService;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.mapper.AlgArticleListMapper;
import com.beyond.algo.mapper.AlgModuleMapper;
import com.beyond.algo.mapper.AlgStarMapper;
import com.beyond.algo.vo.AlgArticleListVo;
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

    @Autowired
    private AlgArticleListMapper algArticleListMapper;

    @Override
    public List<CollectArticlesVo> collectArticles(CollectArticlesVo collectArticlesVo)throws AlgException {
        //分页处理
        PageHelper.startPage(collectArticlesVo.getPage(), collectArticlesVo.getRows());
        List<CollectArticlesVo>collectArticlesVoList=algStarMapper.selectArticles(collectArticlesVo);
        return collectArticlesVoList;
    }
    @Override
    public List getRankList()throws AlgException{
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
    @Override
    public List<AlgArticleListVo>searchArticles(AlgArticleListVo algArticleListVo)throws AlgException{
        List<AlgArticleListVo>  algArticleListVoList=algArticleListMapper.searchArticles(algArticleListVo);
        return algArticleListVoList;
    }

}
