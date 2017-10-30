package com.beyond.algo.algoconsoleboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.vo.AlgArticleListVo;
import com.beyond.algo.vo.CollectArticlesVo;

import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:算法收藏,文献查询，推荐算法
 * @date ：11:25 2017/10/17
 */
public interface AlgorithmCollectAndRankService {
    List<CollectArticlesVo> collectArticles(CollectArticlesVo collectArticlesVo)throws AlgException;
    List getRankList()throws AlgException;
    List<AlgArticleListVo>searchArticles(AlgArticleListVo algArticleListVo)throws AlgException;

}
