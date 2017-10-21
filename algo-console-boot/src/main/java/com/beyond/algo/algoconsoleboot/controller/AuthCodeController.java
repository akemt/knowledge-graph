package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.infra.AuthCodeDomainService;
import com.beyond.algo.algoconsoleboot.infra.AuthCodeService;
import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgAuthCode;
import com.beyond.algo.model.AlgAuthCodeDomain;
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
@RestController
@RequestMapping("/authcode")
public class AuthCodeController {
    private final static Logger logger = LoggerFactory.getLogger(AuthCodeController.class);

    @Autowired
    private AuthCodeService authCodeService;
    @Autowired
    private AuthCodeDomainService authCodeDomainService;

    @RequestMapping(value = "/listauthcode/{usrSn}" ,method = RequestMethod.GET)
    public List<AlgAuthCode> listAuthCode(@PathVariable("usrSn") String usrSn){
        List<AlgAuthCode> result= authCodeService.listUserAuthCode(usrSn);
        return result;
    }
    @RequestMapping(value="/listauthcodedomain/{acdSn}",method = RequestMethod.GET)
    public List<AlgAuthCodeDomain> listAuthCodeDomain(@PathVariable("acdSn") String acdSn){
        List<AlgAuthCodeDomain> result = authCodeDomainService.listAcdSnUrl(acdSn);
        return result;
    }

    @RequestMapping(value="/generatekey",method=RequestMethod.POST)
    public Result generateKey(AlgAuthCode algAuthCode,String[] addUrl){
        authCodeService.generateKey(algAuthCode,addUrl);
        return Result.successResponse();
    }
    @RequestMapping(value = "/deleteauthcode/{acdSn}",method = RequestMethod.GET)
    public Result deleteAuthCode(@PathVariable("acdSn") String acdSn){
        logger.info("主键:{}",acdSn);
        //删除authCode表中内容
        Result result = authCodeService.deleteAuthCode(acdSn);
        return result;
    }
    @RequestMapping(value = "/update",method= RequestMethod.POST)
    public Result update(AlgAuthCode algAuthCode,String[] addUrl){
        logger.info("名字:{}",algAuthCode.getAcdName());
        //更新authcode表
        Result resultAuthCode = authCodeService.updateAuthCode(algAuthCode,addUrl);
        return Result.successResponse();
    }
}
