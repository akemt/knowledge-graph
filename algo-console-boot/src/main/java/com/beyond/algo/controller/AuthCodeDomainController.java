package com.beyond.algo.controller;

import com.beyond.algo.common.Result;
import com.beyond.algo.infra.AuthCodeDomainServer;
import com.beyond.algo.model.AlgAuthCodeDomain;
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

    @Autowired
    private AuthCodeDomainServer authCodeDomainServer;

    @RequestMapping("/list")
    public Result list(){
        Result result = authCodeDomainServer.selectAll();
        return result;
    }
    @RequestMapping("/create")
    public Result create(AlgAuthCodeDomain algAuthCodeDomain){
        Result result = authCodeDomainServer.createAuthCodeDomain(algAuthCodeDomain);
        return result;
    }

    @RequestMapping(value = "/delete/{addSn_id}" , method = RequestMethod.GET)
    public Result delete(@PathVariable("addSn_id") String addSn_id){
        Result result = authCodeDomainServer.deleteAuthCodeDomain(addSn_id);
        return result;
    }

    @RequestMapping("/update")
    public Result update(AlgAuthCodeDomain algAuthCodeDomain){
        Result result = authCodeDomainServer.updataAuthCodeDomain(algAuthCodeDomain);
        return result;
    }
    @RequestMapping(value= "/select/{addSn_id}" , method = RequestMethod.GET)
    public Result select( @PathVariable("addSn_id") String addSn_id){
        Result result = authCodeDomainServer.selectAuthCodeDomain(addSn_id);
        return result;
    }

}
