package com.beyond.algo.algoalgorithmsboot.controller;

import com.beyond.algo.algoalgorithmsboot.infra.AlgModuleListService;
import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgArticleList;
import com.beyond.algo.vo.AlgModuleListVo;
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
public class AlgModuleListController {

    @Autowired
    private AlgModuleListService algModuleListService;
    @Value("server.context-path")
    private String contextPath; //   /algorithms + File.se

    /**
     @Description:我的算法列表
     */
    @RequestMapping(value = "/module/list",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Result listAlg(String catName,String usage,String modName,Integer numPage,Integer numRows,String strId) throws AlgException {
        //log.info()
        if(Assert.isEmpty(numPage)){
            numPage = 1;
        }
        if(Assert.isEmpty(numRows)){
            numRows = 100;
        }
        List<AlgModuleListVo> result = algModuleListService.findModuleList(catName, usage, modName, numPage, numRows,strId);
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
}
