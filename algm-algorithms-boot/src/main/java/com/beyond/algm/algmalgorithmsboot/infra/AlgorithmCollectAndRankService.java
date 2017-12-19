package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.vo.AlgArticleListVo;
import com.beyond.algm.vo.CollectArticlesVo;

import java.util.List;
import java.util.Map;

/**
 * @author ：zhangchuanzhi
 * @Description:算法收藏,文献查询，推荐算法
 * @date ：11:25 2017/10/17
 */
public interface AlgorithmCollectAndRankService {
    List<CollectArticlesVo> collectArticles(CollectArticlesVo collectArticlesVo)throws AlgException;
    List getRankList()throws AlgException;
    List<AlgArticleListVo>searchArticles(AlgArticleListVo algArticleListVo)throws AlgException;


    /**
     * 通过文章标题查询文献列表
     *
     * @param strTitle
     * @return
     * @throws AlgException
     */
    List<Map<String,Object>> queryAlgArticlesListByTitle(String strTitle) throws AlgException;

}
