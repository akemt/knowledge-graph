package com.beyond.algm.algmalgorithmsboot.infra;

import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgArticleList;
import com.beyond.algm.vo.AlgDifDataListVo;
import com.beyond.algm.vo.AlgModuleListVo;
import com.beyond.algm.vo.AlgStarDataVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 1:11
 */
public interface AlgModuleListService {
    List<AlgModuleListVo> findModuleList(String catSn, String usage, String modName, Integer numPage, Integer numRows,String id,String usrCode) throws AlgException;

    //分页的
    PageInfo<AlgModuleListVo> findModulePage(String catSn, String usage, String modName, PageInfo pageInfo, String id, String usrCode) throws AlgException;

    //用户是否收藏了算法标记：star_sn
    PageInfo<AlgModuleListVo> findModuleByStar(String catSn, String usage, String modName, PageInfo pageInfo, String id, String usrCode) throws AlgException;

    //为不同实现功能获取文献信息
    AlgArticleList findAlgArticleList(Integer id) throws AlgException;

    //我的收藏
    Page<AlgModuleListVo> findModuleCollect(String catSn, String usage, String modName, Integer pageNum,Integer pageSize,String usrSn) throws AlgException;

    //数据列表
    List<AlgDifDataListVo> findDifDataList(Integer id) throws AlgException;

    //收藏算法
    AlgStarDataVo modStar(String modSn, String usrSn) throws AlgException;
}
