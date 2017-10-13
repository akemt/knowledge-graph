package com.beyond.algo.controller;

import com.beyond.algo.common.Result;
import com.beyond.algo.infra.AuthCodeDomainService;
import com.beyond.algo.infra.AuthCodeService;
import com.beyond.algo.model.AlgAuthCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/12 0012 下午 9:58
 */
@RestController
@RequestMapping("/authcode")
public class AuthCodeController {
    private final static Logger logger = LoggerFactory.getLogger(AuthCodeController.class);

    @Autowired
    private AuthCodeService authCodeService;

    @RequestMapping("/list")
    public Result list(){
        Result result = authCodeService.selectAll();
        return result;
    }

    @RequestMapping("/create")
    public Result create(AlgAuthCode algAuthCode){
        logger.info("名字:{},",algAuthCode.getAcdName());
        Result result = authCodeService.createAuthCode(algAuthCode);
        return result;
    }

    @RequestMapping(value = "/delect/{acdSn_id}",method = RequestMethod.GET)
    public Result delete(@PathVariable("acdSn_id") String acdSn_id){
        logger.info("主键:{}",acdSn_id);
        Result result = authCodeService.deleteAuthCode(acdSn_id);
        return result;
    }
    @RequestMapping("/update")
    public Result update(AlgAuthCode algAuthCode){
        logger.info("名字:{}",algAuthCode.getAcdName());
        Result result = authCodeService.updateAuthCode(algAuthCode);
        return result;
    }
    @RequestMapping(value = "/selsct/{acdSn_id}",method = RequestMethod.GET)
    public Result select(String acdSn_id){
        logger.info("主键:{}",acdSn_id);
        Result result = authCodeService.selectAuthCode(acdSn_id);
        return result;
    }

}
