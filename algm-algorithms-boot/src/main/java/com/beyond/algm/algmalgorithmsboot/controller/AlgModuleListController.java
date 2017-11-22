package com.beyond.algm.algmalgorithmsboot.controller;

import com.beyond.algm.algmalgorithmsboot.base.BaseController;
import com.beyond.algm.algmalgorithmsboot.infra.AlgModuleListService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.Result;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgArticleList;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgDifDataListVo;
import com.beyond.algm.vo.AlgModuleListVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 12:12
 */
@Slf4j
@RestController
public class AlgModuleListController extends BaseController {

    @Autowired
    private AlgModuleListService algModuleListService;
    @Value("server.context-path")
    private String contextPath; //   /algorithms + File.se

    /**
     @Description:我的算法列表
     */
    @RequestMapping(value = "/module/list",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Result listAlg(String catSn,String usage,String modName,Integer numPage,Integer numRows,String id) throws AlgException {
        //log.info()
        if(Assert.isEmpty(numPage)){
            numPage = 1;
        }
        if(Assert.isEmpty(numRows)){
            numRows = 100;
        }
        List<AlgModuleListVo> result = algModuleListService.findModuleList(catSn, usage, modName, numPage, numRows,id,null);
        return Result.ok(result);
    }

    /**
     @Description:不同实现-获取文献信息
     */
    @RequestMapping(value = "/module/difrealize",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Result difRealize(Integer id) throws AlgException {
        //获取文献信息
        AlgArticleList algArticleList = algModuleListService.findAlgArticleList(id);
        return Result.ok(algArticleList);
    }

    /**
     @Description:我的算法(无参)
     */
    @RequestMapping(value = "/module/mylist",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Result myListAlg(Integer numPage,Integer numRows) throws AlgException {
        //log.info()
        if(Assert.isEmpty(numPage)){
            numPage = 1;
        }
        if(Assert.isEmpty(numRows)){
            numRows = 100;
        }
        AlgUser algUser = getUserInfo();
        List<AlgModuleListVo> result = algModuleListService.findModuleList(null, null, null, numPage, numRows,null,algUser.getUsrCode());
        return Result.ok(result);
    }

    /**
     @Description:我的收藏
     */
    @RequestMapping(value = "/module/collect",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Result listAlgcollect(String catSn,String usage,String modName,Integer numPage,Integer numRows) throws AlgException {
        if(Assert.isEmpty(numPage)){
            numPage = 1;
        }
        if(Assert.isEmpty(numRows)){
            numRows = 100;
        }
        AlgUser algUser = getUserInfo();
        List<AlgModuleListVo> result = algModuleListService.findModuleCollect(catSn, usage, modName, numPage, numRows,algUser.getUsrSn());
        return Result.ok(result);
    }

    /**
     @Description:数据列表
     */
    @RequestMapping(value = "/module/difdata",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Result difDataList(Integer id) throws AlgException {
        List<AlgDifDataListVo> result = algModuleListService.findDifDataList(id);
        return Result.ok(result);
    }
}