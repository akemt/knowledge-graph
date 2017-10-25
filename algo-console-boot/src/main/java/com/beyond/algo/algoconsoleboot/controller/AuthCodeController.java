package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.infra.AuthCodeDomainService;
import com.beyond.algo.algoconsoleboot.infra.AuthCodeService;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.model.AlgAuthCode;
import com.beyond.algo.model.AlgAuthCodeDomain;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/12 0012 下午 9:58
 */
@Slf4j
@RestController
@RequestMapping("/authCode")
public class AuthCodeController {
    private final static Logger logger = LoggerFactory.getLogger(AuthCodeController.class);

    @Autowired
    private AuthCodeService authCodeService;
    @Autowired
    private AuthCodeDomainService authCodeDomainService;

    @RequestMapping(value = "/listAuthCode/{usrSn}" ,method = RequestMethod.GET)
    public Result listAuthCode(@PathVariable("usrSn") String usrSn){
        try {
            List<AlgAuthCode> result= authCodeService.listUserAuthCode(usrSn);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("返回authCode表中内容失败", e);
            e.printStackTrace();
            return  new Result<>(ResultEnum.FAILURE.code,e.getMessage());
        }
    }
    @RequestMapping(value="/listAuthCodeDomain/{acdSn}",method = RequestMethod.GET)
    public Result listAuthCodeDomain(@PathVariable("acdSn") String acdSn){
        try {
            List<AlgAuthCodeDomain> result = authCodeDomainService.listAcdSnUrl(acdSn);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("返回authCodeDomain表中内容失败");
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code,e.getMessage());
        }
    }

    @RequestMapping(value="/generateKey",method=RequestMethod.POST)
    public Result generateKey(AlgAuthCode algAuthCode,String[] addUrl){
        try {
            authCodeService.generateKey(algAuthCode,addUrl);
            return Result.successResponse();
        } catch (Exception e) {
            log.error("生成KEY失败");
            e.printStackTrace();
            return Result.failureResponse();
        }
    }
    @RequestMapping(value = "/deleteAuthCode/{acdSn}",method = RequestMethod.GET)
    public Result deleteAuthCode(@PathVariable("acdSn") String acdSn){
        logger.info("主键:{}",acdSn);
        try {
            //删除authCode表中内容
            Result result = authCodeService.deleteAuthCode(acdSn);
            return result;
        } catch (Exception e) {
            log.error("删除authCode表失败");
            e.printStackTrace();
            return Result.failureResponse();
        }
    }
    @RequestMapping(value = "/update",method= RequestMethod.POST)
    public Result update(AlgAuthCode algAuthCode,String[] addUrl){
        logger.info("名字:{}",algAuthCode.getAcdName());
        try {
            //更新authcode表
            Result resultAuthCode = authCodeService.updateAuthCode(algAuthCode,addUrl);
            return Result.successResponse();
        } catch (Exception e) {
            log.error("更新authCode表失败");
            e.printStackTrace();
            return Result.failureResponse();
        }
    }
}
