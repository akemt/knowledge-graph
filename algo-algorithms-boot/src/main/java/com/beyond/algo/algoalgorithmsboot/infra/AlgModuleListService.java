package com.beyond.algo.algoalgorithmsboot.infra;

import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgArticleList;
import com.beyond.algo.vo.AlgModuleListVo;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 1:11
 */
public interface AlgModuleListService {
    List<AlgModuleListVo> findModuleList(String catName, String usage, String modName, Integer numPage, Integer numRows,String id) throws AlgException;
    //为不同实现功能获取文献信息
    AlgArticleList findAlgArticleList(Integer id) throws AlgException;
}
