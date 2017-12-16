package com.beyond.algm.algmalgorithmsboot.controller;

import com.beyond.algm.algmalgorithmsboot.base.BaseController;
import com.beyond.algm.algmalgorithmsboot.infra.*;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ContentController extends BaseController {
    @Autowired
    private UseAlgorithmService useAlgorithmService;
    @Autowired
    private AlgorithmCollectAndRankService algorithmCollectAndRankService;
    @Autowired
    private AlgorithmDetailService algorithmDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;
    /**
     * @author ：zhangchuanzhi
     * @Description:用户使用情况
     * @param：AlgRUserModuleCallTransVo
     * @Modify By :zhangchuanzhi
     * @date ：14:07 2017/10/11
     */
    @RequestMapping(value="/algorithmRecord", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageInfo<AlgRUserModuleCallTransVo>> algorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo,PageInfo pageInfo) throws AlgException{
        pageInfo.setPageNum(pageInfo.getPageNum()==0?1 : pageInfo.getPageNum());
        pageInfo.setPageSize(pageInfo.getPageSize()==0?10 : pageInfo.getPageSize());
        AlgUser algUser = getUserInfo();
        algRUserModuleCallTransVo.setCallUsrSn(algUser.getUsrSn());
        log.info("调用用户id:{},Page:{},Row:{}",algRUserModuleCallTransVo.getCallUsrSn(),algRUserModuleCallTransVo.getPage(),algRUserModuleCallTransVo.getRows());
        PageInfo<AlgRUserModuleCallTransVo> algRUserModuleCallTransList= useAlgorithmService.algorithmRecord(algRUserModuleCallTransVo, pageInfo);
        return Result.ok(algRUserModuleCallTransList);
    }
    /**
     * @author ：zhangchuanzhi
     * @Description:用户收益情况
     * @param：AlgRUserModuleCallTransVo
     * @Modify By :zhangchuanzhi
     * @date ：17:07 2017/10/12
     */
    @RequestMapping(value="/algorithmEarnRecord", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageInfo<AlgRUserModuleCallTransVo>> earnRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo,PageInfo pageInfo) throws AlgException{
        pageInfo.setPageNum(pageInfo.getPageNum()==0?1 : pageInfo.getPageNum());
        pageInfo.setPageSize(pageInfo.getPageSize()==0?10 : pageInfo.getPageSize());
        AlgUser algUser = getUserInfo();
        algRUserModuleCallTransVo.setOwnerUsrSn(algUser.getUsrSn());
        log.info("算法创建者id:{},Page:{},Row:{}",algRUserModuleCallTransVo.getOwnerUsrSn(),algRUserModuleCallTransVo.getPage(),algRUserModuleCallTransVo.getRows());
        PageInfo<AlgRUserModuleCallTransVo> algRUserModuleCallTransList= useAlgorithmService.earnRecord(algRUserModuleCallTransVo, pageInfo);
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
    @RequestMapping(value="/{usrCode}/{modId}/algorithmdetail", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result getAlgorithmDetail(@PathVariable("usrCode") String usrCode, @PathVariable("modId") String modId ) throws AlgException {
        log.info("查看算法用户:{},算法模块项目名称id:{}",usrCode,modId);
        AlgUser algUser = getUserInfo();
        if(Assert.isNotNULL(algUser)){
            authService. isModuleByUser( usrCode,modId, algUser.getUsrCode(),algUser.getUsrSn());
            AlgorithmDetailVo algorithmDetailVo=new AlgorithmDetailVo();
            algorithmDetailVo.setModId(modId);
            algorithmDetailVo.setUsrSn(algUser.getUsrSn());
            algorithmDetailVo.setUsrCode(usrCode);
            AlgModuleVo algModuleVo = algorithmDetailService.getAlgorithmDetail(algorithmDetailVo);
            if(Assert.isNULL(algModuleVo)){
                String[] checkMessage = {"查询失败"};
                throw new AlgException("BEYOND.ALG.MODILE.VALID.0000011",checkMessage);
            }
            return Result.ok(algModuleVo);
        }
        return null;
    }
}
