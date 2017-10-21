package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.infra.AlgorithmCollectAndRankService;
import com.beyond.algo.algoconsoleboot.infra.UseAlgorithmService;
import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.vo.AlgArticleListVo;
import com.beyond.algo.vo.AlgRUserModuleCallTransVo;
import com.beyond.algo.vo.CollectArticlesVo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class ContentController {
    @Autowired
    private UseAlgorithmService useAlgorithmService;
    @Autowired
    private AlgorithmCollectAndRankService algorithmCollectAndRankService;
    /**
     * @author ：zhangchuanzhi
     * @Description:用户使用情况
     * @param：AlgRUserModuleCallTransVo
     * @Modify By :zhangchuanzhi
     * @date ：14:07 2017/10/11
     */
    @RequestMapping(value="/algorithmRecord", method= RequestMethod.POST)
    @ResponseBody
    public Result algorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo) {
        log.info("调用用户id:{},Page:{},Row:{}",algRUserModuleCallTransVo.getCallUsrSn(),algRUserModuleCallTransVo.getPage(),algRUserModuleCallTransVo.getRows());
        try {
            List<AlgRUserModuleCallTransVo> algRUserModuleCallTransList= useAlgorithmService.algorithmRecord(algRUserModuleCallTransVo);
            if(Assert.isNotEmpty(algRUserModuleCallTransList)){
                return Result.ok(algRUserModuleCallTransList);
            }else{
                return Result.failure(algRUserModuleCallTransList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 待完善异常处理
            return Result.failureResponse();
        }
    }
    /**
     * @author ：zhangchuanzhi
     * @Description:用户收益情况
     * @param：AlgRUserModuleCallTransVo
     * @Modify By :zhangchuanzhi
     * @date ：17:07 2017/10/12
     */
    @RequestMapping(value="/algorithmEarnRecord", method= RequestMethod.POST)
    @ResponseBody
    public Result earnRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo) {
        log.info("算法创建者id:{},Page:{},Row:{}",algRUserModuleCallTransVo.getOwnerUsrSn(),algRUserModuleCallTransVo.getPage(),algRUserModuleCallTransVo.getRows());
        try {
            List<AlgRUserModuleCallTransVo> algRUserModuleCallTransList= useAlgorithmService.earnRecord(algRUserModuleCallTransVo);
            if(Assert.isNotEmpty(algRUserModuleCallTransList)){
                return Result.ok(algRUserModuleCallTransList);
            }else{
                return Result.failure(algRUserModuleCallTransList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 待完善异常处理
            return Result.failureResponse();
        }
    }


    /**
     * @author ：zhangchuanzhi
     * @Description:用户收藏文献
     * @param：CollectArticlesVo
     * @Modify By :zhangchuanzhi
     * @date ：10:07 2017/10/19
     */
    @RequestMapping(value="/collectArticles", method= RequestMethod.POST)
    @ResponseBody
    public Result collectArticles(CollectArticlesVo collectArticlesVo) {
        log.info("用户id:{},Page:{},Row:{}",collectArticlesVo.getUsrSn(),collectArticlesVo.getPage(),collectArticlesVo.getRows());
        try {
            List<CollectArticlesVo> collectArticles= algorithmCollectAndRankService.collectArticles(collectArticlesVo);
            if(Assert.isNotEmpty(collectArticles)){
                return Result.ok(collectArticles);
            }else{
                return Result.failure(collectArticles);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 待完善异常处理
            return Result.failureResponse();
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户推荐算法
     * @param：CollectArticlesVo
     * @Modify By :zhangchuanzhi
     * @date ：14:22 2017/10/19
     */
    @RequestMapping(value="/algorithmRank", method= RequestMethod.POST)
    @ResponseBody
    public Result algorithmRank( ) {
     //   log.info("用户id:{},Page:{},Row:{}",collectArticlesVo.getUsrSn(),collectArticlesVo.getPage(),collectArticlesVo.getRows());
        try {
            List bcv= algorithmCollectAndRankService.getRankList();
            if(Assert.isNotEmpty(bcv)){
                return Result.ok(bcv);
            }else{
                return Result.failure(bcv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 待完善异常处理
            return Result.failureResponse();
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:文献查询
     * @param：CollectArticlesVo
     * @Modify By :zhangchuanzhi
     * @date ：14:22 2017/10/19
     */
    @RequestMapping(value="/searchArticles", method= RequestMethod.POST)
    @ResponseBody
    public Result searchArticles(AlgArticleListVo algArticleListVo ) {
           log.info("用户id:{}",algArticleListVo.getId());
        try {
            List<AlgArticleListVo> articlesList= algorithmCollectAndRankService.searchArticles(algArticleListVo);
            if(Assert.isNotEmpty(articlesList)){
                return Result.ok(articlesList);
            }else{
                return Result.failure(articlesList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 待完善异常处理
            return Result.failureResponse();
        }
    }
}
