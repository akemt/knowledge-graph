package com.beyond.algo.controller;

import com.beyond.algo.common.Result;
import com.beyond.algo.common.UUIDUtil;
import com.beyond.algo.infra.AuthCodeDomainService;
import com.beyond.algo.infra.AuthCodeService;
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
    public List<AlgAuthCode> listauthcode(@PathVariable("usrSn") String usrSn){
        List<AlgAuthCode> result= authCodeService.listUserAuthCode(usrSn);
        return result;
    }
    @RequestMapping(value="/listauthcodedomain/{acdSn}",method = RequestMethod.GET)
    public List<AlgAuthCodeDomain> listauthcodedomain(@PathVariable("acdSn") String acdSn){
        List<AlgAuthCodeDomain> result = authCodeDomainService.listAcdSnUrl(acdSn);
        return result;
    }

    @RequestMapping( value = "/create" , method=RequestMethod.POST)
    public Result create(AlgAuthCode algAuthCode,String[] addUrl){
        logger.info("名字:{},",algAuthCode.getAcdName());
        String acdSn = UUIDUtil.createUUID();
        algAuthCode.setAcdSn(acdSn);
        if(addUrl != null){
            for (String anAddUrl : addUrl) {
                AlgAuthCodeDomain algAuthCodeDomain = new AlgAuthCodeDomain();
                // assert algAuthCodeDomain != null;
                String addSn = UUIDUtil.createUUID();
                algAuthCodeDomain.setAddSn(addSn);
                algAuthCodeDomain.setAcdSn(acdSn);
                algAuthCodeDomain.setAddUrl(anAddUrl);
                Result resultDomain = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomain);
                if(resultDomain.getMsg() != "成功"){
                    return Result.failureResponse();
                }
            }
        }
        Result result=authCodeService.createAuthCode(algAuthCode);
        return result;
    }


    @RequestMapping(value = "/deleteauthcode/{acdSn}",method = RequestMethod.GET)
    public Result deleteauthcode(@PathVariable("acdSn") String acdSn){
        logger.info("主键:{}",acdSn);
        Result result = authCodeService.deleteAuthCode(acdSn);
        List<AlgAuthCodeDomain> resultAuthDomain = authCodeDomainService.listAcdSnUrl(acdSn);
        if (resultAuthDomain != null)
        {
            authCodeDomainService.deleteByAcdSn(acdSn);
        }
        return result;
    }

    @RequestMapping("/update")
    public Result update(AlgAuthCode algAuthCode){
        logger.info("名字:{}",algAuthCode.getAcdName());
        Result result = authCodeService.updateAuthCode(algAuthCode);
        return result;
    }
    @RequestMapping(value = "/select/{acdSn_id}",method = RequestMethod.GET)
    public Result select(@PathVariable("acdSn_id") String acdSn_id){
        logger.info("主键:{}",acdSn_id);
        Result result = authCodeService.selectAuthCode(acdSn_id);
        return result;
    }

}
