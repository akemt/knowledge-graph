package com.beyond.algo.algoalgorithmsboot.controller;

import com.beyond.algo.algoalgorithmsboot.base.BaseController;
import com.beyond.algo.algoalgorithmsboot.infra.AlgorithmCollectAndRankService;
import com.beyond.algo.algoalgorithmsboot.infra.AlgorithmDetailService;
import com.beyond.algo.algoalgorithmsboot.infra.UseAlgorithmService;
import com.beyond.algo.common.Result;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AlgArticleListVo;
import com.beyond.algo.vo.AlgModuleVo;
import com.beyond.algo.vo.AlgRUserModuleCallTransVo;
import com.beyond.algo.vo.CollectArticlesVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:用户和商城内容controller
 * @date ：12:43 2017/10/12
 */
@Slf4j
@RestController
@RequestMapping("/content")
public class ContentController extends BaseController {
    @Autowired
    private UseAlgorithmService useAlgorithmService;
    @Autowired
    private AlgorithmCollectAndRankService algorithmCollectAndRankService;
    @Autowired
    private AlgorithmDetailService algorithmDetailService;
    /**
     * @author ：zhangchuanzhi
     * @Description:用户使用情况
     * @param：AlgRUserModuleCallTransVo
     * @Modify By :zhangchuanzhi
     * @date ：14:07 2017/10/11
     */
    @RequestMapping(value="/algorithmRecord", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result algorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo) throws AlgException{
        AlgUser algUser = getUserInfo();
        algRUserModuleCallTransVo.setCallUsrSn(algUser.getUsrSn());
        log.info("调用用户id:{},Page:{},Row:{}",algRUserModuleCallTransVo.getCallUsrSn(),algRUserModuleCallTransVo.getPage(),algRUserModuleCallTransVo.getRows());
        List<AlgRUserModuleCallTransVo> algRUserModuleCallTransList= useAlgorithmService.algorithmRecord(algRUserModuleCallTransVo);
        return Result.ok(algRUserModuleCallTransList);
    }
    /**
     * @author ：zhangchuanzhi
     * @Description:用户收益情况
     * @param：AlgRUserModuleCallTransVo
     * @Modify By :zhangchuanzhi
     * @date ：17:07 2017/10/12
     */
    @RequestMapping(value="/algorithmEarnRecord", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result earnRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo) throws AlgException{
        AlgUser algUser = getUserInfo();
        algRUserModuleCallTransVo.setOwnerUsrSn(algUser.getUsrSn());
        log.info("算法创建者id:{},Page:{},Row:{}",algRUserModuleCallTransVo.getOwnerUsrSn(),algRUserModuleCallTransVo.getPage(),algRUserModuleCallTransVo.getRows());
        List<AlgRUserModuleCallTransVo> algRUserModuleCallTransList= useAlgorithmService.earnRecord(algRUserModuleCallTransVo);
        return Result.ok(algRUserModuleCallTransList);

    }


    /**
     * @author ：zhangchuanzhi
     * @Description:用户收藏文献
     * @param：CollectArticlesVo
     * @Modify By :zhangchuanzhi
     * @date ：10:07 2017/10/19
     */
    @RequestMapping(value="/collectArticles", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result collectArticles(CollectArticlesVo collectArticlesVo) throws AlgException{
        AlgUser algUser = getUserInfo();
        collectArticlesVo.setUsrSn(algUser.getUsrSn());
        log.info("用户id:{},Page:{},Row:{}",collectArticlesVo.getUsrSn(),collectArticlesVo.getPage(),collectArticlesVo.getRows());
        List<CollectArticlesVo> collectArticles= algorithmCollectAndRankService.collectArticles(collectArticlesVo);
        return Result.ok(collectArticles);

    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户推荐算法
     * @param：CollectArticlesVo
     * @Modify By :zhangchuanzhi
     * @date ：14:22 2017/10/19
     */
    @RequestMapping(value="/algorithmRank", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result algorithmRank( )throws AlgException {
     //   log.info("用户id:{},Page:{},Row:{}",collectArticlesVo.getUsrSn(),collectArticlesVo.getPage(),collectArticlesVo.getRows());
            List bcv= algorithmCollectAndRankService.getRankList();
            return Result.ok(bcv);
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:文献查询
     * @param：CollectArticlesVo
     * @Modify By :zhangchuanzhi
     * @date ：14:22 2017/10/19
     */
    @RequestMapping(value="/searchArticles", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result searchArticles(AlgArticleListVo algArticleListVo ) throws AlgException{
        log.info("用户id:{}",algArticleListVo.getId());
        List<AlgArticleListVo> articlesList= algorithmCollectAndRankService.searchArticles(algArticleListVo);
        return Result.ok(articlesList);
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:获得算法详情
     * @param：CollectArticlesVo
     * @Modify By :zhangchuanzhi
     * @date ：14:22 2017/10/19
     */
    @RequestMapping(value="/getAlgorithmDetail", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result getAlgorithmDetail(String modSn ) throws AlgException {
        log.info("算法模块id:{}",modSn);
        AlgModuleVo algModuleVo = algorithmDetailService.getAlgorithmDetail(modSn);
        return Result.ok(algModuleVo);
    }
}
