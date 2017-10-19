package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.vo.CollectArticlesVo;

import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:算法收藏
 * @date ：11:25 2017/10/17
 */
public interface AlgorithmCollectAndRankService {
    List<CollectArticlesVo> collectArticles(CollectArticlesVo collectArticlesVo);
    List getRankList();
}
