package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.common.Result;
import com.beyond.algo.algoconsoleboot.infra.AuthCodeDomainService;
import com.beyond.algo.model.AlgAuthCodeDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @Description:授权码限制域名操作(增删改查)
 * @version Created in：2017/9/28 0028 上午 10:29
 */
@RestController
@RequestMapping("/authcodedomain")
public class AuthCodeDomainController {
    private final static Logger logger = LoggerFactory.getLogger(AuthCodeDomainController.class);

    @Autowired
    private AuthCodeDomainService authCodeDomainService;

    @RequestMapping("/list")
    public Result list(){
        Result result = authCodeDomainService.selectAll();
        return result;
    }
    @RequestMapping("/create")
    public Result create(AlgAuthCodeDomain algAuthCodeDomain){
        logger.info("Url:{}",algAuthCodeDomain.getAddUrl());
        Result result = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomain);
        return result;
    }

    @RequestMapping(value = "/delete/{addSn_id}" , method = RequestMethod.GET)
    public Result delete(@PathVariable("addSn_id") String addSn_id){
        logger.info("主键:{}",addSn_id);
        Result result = authCodeDomainService.deleteAuthCodeDomain(addSn_id);
        return result;
    }

    @RequestMapping("/update")
    public Result update(AlgAuthCodeDomain algAuthCodeDomain){
        logger.info("Url:{}",algAuthCodeDomain.getAddSn());
        Result result = authCodeDomainService.updataAuthCodeDomain(algAuthCodeDomain);
        return result;
    }
    @RequestMapping(value= "/select/{addSn_id}" , method = RequestMethod.GET)
    public Result select( @PathVariable("addSn_id") String addSn_id){
        logger.info("主键:{}",addSn_id);
        Result result = authCodeDomainService.selectAuthCodeDomain(addSn_id);
        return result;
    }

}
