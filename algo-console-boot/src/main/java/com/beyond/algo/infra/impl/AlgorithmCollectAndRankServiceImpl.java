package com.beyond.algo.infra.impl;

import com.beyond.algo.infra.AlgorithmCollectAndRankService;
import com.beyond.algo.mapper.AlgStarMapper;
import com.beyond.algo.vo.CollectArticlesVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<CollectArticlesVo> collectArticles(CollectArticlesVo collectArticlesVo){
        //分页处理
        PageHelper.startPage(collectArticlesVo.getPage(), collectArticlesVo.getRows());
        List<CollectArticlesVo>collectArticlesVoList=algStarMapper.selectArticles(collectArticlesVo);
        return collectArticlesVoList;
    }
}
