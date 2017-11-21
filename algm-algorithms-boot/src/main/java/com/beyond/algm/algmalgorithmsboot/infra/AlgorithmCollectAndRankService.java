package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.vo.AlgArticleListVo;
import com.beyond.algm.vo.CollectArticlesVo;

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
