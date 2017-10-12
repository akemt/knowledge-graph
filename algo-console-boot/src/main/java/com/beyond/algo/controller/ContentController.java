package com.beyond.algo.controller;

import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.infra.UseAlgorithmService;
import com.beyond.algo.model.AlgCashTrans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
@RequestMapping("/content")
public class ContentController {
 /*   private final static Logger logger = LoggerFactory.getLogger(ContentController.class);
    @Autowired
    private UseAlgorithmService useAlgorithmService;
    *//**
     * @author ：zhangchuanzhi
     * @Description:用户使用情况
     * @param：AlgRUserModuleCallTransVo
     * @Modify By :zhangchuanzhi
     * @date ：14:07 2017/10/11
     *//*
    @RequestMapping(value="/algorithmRecord", method= RequestMethod.POST)
    @ResponseBody
    public Result algorithmRecord(AlgRUserModuleCallTransVo algRUserModuleCallTransVo) {
        logger.info("用户id:{},Page:{},Row:{}",algRUserModuleCallTransVo.getCallUsrSn(),algRUserModuleCallTransVo.getPage(),algRUserModuleCallTransVo.getRows());
        List<AlgRUserModuleCallTransVo> algRUserModuleCallTransList= useAlgorithmService.algorithmRecord(algRUserModuleCallTransVo);
        if(Assert.isNotEmpty(algRUserModuleCallTransList)){
            return Result.ok(algRUserModuleCallTransList);
        }else{
            return Result.failure(algRUserModuleCallTransList);
        }
    }*/
}
