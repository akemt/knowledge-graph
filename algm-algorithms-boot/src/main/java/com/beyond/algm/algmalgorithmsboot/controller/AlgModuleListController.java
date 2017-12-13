package com.beyond.algm.algmalgorithmsboot.controller;

import com.beyond.algm.algmalgorithmsboot.base.BaseController;
import com.beyond.algm.algmalgorithmsboot.infra.AlgModuleListService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.common.Result;
import com.beyond.algm.common.ResultEnum;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgArticleList;
import com.beyond.algm.model.AlgModule;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.AlgDifDataListVo;
import com.beyond.algm.vo.AlgModuleListVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public  Result listAlg(String catSn,String usage,String modName,Integer pageNum,Integer pageSize,String id) throws AlgException {
        //log.info()
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;

        Page<AlgModuleListVo> page = algModuleListService.findModulePage(catSn, usage, modName, pageNum, pageSize,id,null);
        PageInfo pageInfo = new PageInfo(page);

        return Result.ok(pageInfo);
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
    public  Result myListAlg(Integer pageNum,Integer pageSize) throws AlgException {
        //log.info()
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        AlgUser algUser = getUserInfo();
        Page<AlgModuleListVo> page = algModuleListService.findModulePage(null, null, null, pageNum, pageSize,null,algUser.getUsrCode());
        PageInfo pageInfo = new PageInfo(page);
        return Result.ok(pageInfo);
    }

    /**
     @Description:我的收藏
     */
    @RequestMapping(value = "/module/collect",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Result listAlgcollect(String catSn,String usage,String modName,Integer pageNum,Integer pageSize) throws AlgException {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        AlgUser algUser = getUserInfo();
        Page<AlgModuleListVo> page = algModuleListService.findModuleCollect(catSn, usage, modName, pageNum, pageSize,algUser.getUsrSn());
        PageInfo pageInfo = new PageInfo(page);
        return Result.ok(pageInfo);
    }

    /**
     @Description:数据列表
     */
    @RequestMapping(value = "/module/difdata",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Result difDataList(Integer id) throws AlgException {
        List<AlgDifDataListVo> result = algModuleListService.findDifDataList(id);
        return Result.ok(result);
    }

    /**
     @Description:不同实现(替换：“我的算法列表listAlg + 不同实现-获取文献信息difRealize + 数据列表difDataList”三个请求)
     */
    @RequestMapping(value = "/module/difreal",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  Result difRealize1(Integer numPage,Integer numRows,Integer id) throws AlgException {
            log.info("文章ID: id {} ", id);
            if(Assert.isEmpty(numPage)){
                numPage = 1;
            }
            if(Assert.isEmpty(numRows)){
                numRows = 100;
            }
            Map<String,Object> difMap=new HashMap<String,Object>();
            //算法列表
            AlgArticleList algArticleList = algModuleListService.findAlgArticleList(id);
            difMap.put("algArticleList",algArticleList);
            //文献信息
            String idd = id.toString();
            List<AlgModuleListVo> literature = algModuleListService.findModuleList(null, null, null, numPage, numRows,idd,null);
            difMap.put("literature",literature);
            //数据列表
            List<AlgDifDataListVo> dataList = algModuleListService.findDifDataList(id);
            difMap.put("dataList",dataList);
            return Result.ok(difMap);
    }

    /**
     @Description:收藏算法
     * @param modSn
     * @return
     * @throws AlgException
     */
    @RequestMapping(value = "/module/star", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result AddAlgorithm(String modSn) throws AlgException {
        AlgUser algUser = getUserInfo();
        algModuleListService.modStar(modSn,algUser.getUsrSn());
        return Result.successResponse();
    }
}