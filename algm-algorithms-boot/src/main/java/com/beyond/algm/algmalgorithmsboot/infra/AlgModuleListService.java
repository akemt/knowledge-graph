package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgArticleList;
import com.beyond.algm.vo.AlgModuleListVo;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 1:11
 */
public interface AlgModuleListService {
    List<AlgModuleListVo> findModuleList(String catSn, String usage, String modName, Integer numPage, Integer numRows,String id,String usrCode) throws AlgException;

    //为不同实现功能获取文献信息
    AlgArticleList findAlgArticleList(Integer id) throws AlgException;

    //我的收藏
    List<AlgModuleListVo> findModuleCollect(String catSn, String usage, String modName, Integer numPage, Integer numRows,String usrSn) throws AlgException;
}
