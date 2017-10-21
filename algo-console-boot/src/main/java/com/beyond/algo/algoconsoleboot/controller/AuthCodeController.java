package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.infra.AuthCodeDomainService;
import com.beyond.algo.algoconsoleboot.infra.AuthCodeService;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.UUIDUtil;
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
import java.util.Objects;

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
        /*//主键插入
        String acdSn = UUIDUtil.createUUID();
        algAuthCode.setAcdSn(acdSn);
        //授权码生成，后期看看用别的方法生成串号
        String acdId = "Beyond" + UUIDUtil.getRandomString(15) + "1";
        algAuthCode.setAcdId(acdId);*/
        authCodeService.createAuthCode(algAuthCode,addUrl);

        /*//插入默认的Url “algo://*”
        AlgAuthCodeDomain algAuthCodeDomainDefault  = new AlgAuthCodeDomain();
        String addSnDefault = UUIDUtil.createUUID();
        algAuthCodeDomainDefault.setAddSn(addSnDefault);
        algAuthCodeDomainDefault.setAcdSn(algAuthCode.getAcdSn());
        algAuthCodeDomainDefault.setAddUrl("algo://*");
        Result resultDomainDefault = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomainDefault);
*/
        /*//插入传入自定义的Url
        if(addUrl != null){
            for (String anAddUrl : addUrl) {
                AlgAuthCodeDomain algAuthCodeDomain = new AlgAuthCodeDomain();
                String addSn = UUIDUtil.createUUID();
                algAuthCodeDomain.setAddSn(addSn);
                algAuthCodeDomain.setAcdSn(algAuthCode.getAcdSn());
                algAuthCodeDomain.setAddUrl(anAddUrl);
                Result resultDomain = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomain);
                if(resultDomain.getMsg() != "成功"){
                    return Result.failureResponse();
                }
            }
        }*/
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
        Result resultAuthCode = authCodeService.updateAuthCode(algAuthCode);
        if(resultAuthCode.getMsg()!="成功"){
            return Result.failureResponse();
        }
        //更新authcodeDomain表之前先将表中这个key下Url全部删除
        authCodeDomainService.deleteByAcdSn(algAuthCode.getAcdSn());
        //重新插入前端传过来的Url
        //插入默认的Url “algo://*”
        AlgAuthCodeDomain algAuthCodeDomainDefault  = new AlgAuthCodeDomain();
        String addSnDefault = UUIDUtil.createUUID();
        algAuthCodeDomainDefault.setAddSn(addSnDefault);
        algAuthCodeDomainDefault.setAcdSn(algAuthCode.getAcdSn());
        algAuthCodeDomainDefault.setAddUrl("algo://*");
        Result resultDomainDefault = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomainDefault);
        if(resultDomainDefault.getMsg() != "成功"){
            return Result.failureResponse();
        }
        //插入传入自定义的Url
        if(addUrl != null){
            for (String anAddUrl : addUrl) {
                AlgAuthCodeDomain algAuthCodeDomain = new AlgAuthCodeDomain();
                String addSn = UUIDUtil.createUUID();
                algAuthCodeDomain.setAddSn(addSn);
                algAuthCodeDomain.setAcdSn(algAuthCode.getAcdSn());
                algAuthCodeDomain.setAddUrl(anAddUrl);
                Result resultDomain = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomain);
                if(resultDomain.getMsg() != "成功"){
                    return Result.failureResponse();
                }
            }
        }
        return Result.successResponse();
    }

/*    @RequestMapping(value = "/createauthcodedomain",method = RequestMethod.POST)
    public Result createauthcodedomain(AlgAuthCode algAuthCode,String addUrl){
        String addSn = UUIDUtil.createUUID();
        AlgAuthCodeDomain algAuthCodeDomain = new AlgAuthCodeDomain();
        algAuthCodeDomain.setAcdSn(addSn);
        algAuthCodeDomain.setAddUrl(addUrl);
        algAuthCodeDomain.setAcdSn(algAuthCode.getAcdSn());
        Result result = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomain);
        return result;
    }*/

    /*@RequestMapping( value = "/create" , method=RequestMethod.POST)
    public Result create(AlgAuthCode algAuthCode){
        logger.info("名字:{},",algAuthCode.getAcdName());
        String acdSn = UUIDUtil.createUUID();
        algAuthCode.setAcdSn(acdSn);
        AlgAuthCodeDomain algAuthCodeDomain = new AlgAuthCodeDomain();
        algAuthCodeDomain.setAddUrl("algo://*");
        String addSn = UUIDUtil.createUUID();
        algAuthCodeDomain.setAddSn(addSn);
        algAuthCodeDomain.setAcdSn(acdSn);
        Result resultDomain = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomain);
        if(resultDomain.getMsg() != "成功"){
            return Result.failureResponse();
        }
*//*        if(addUrl != null){
            for (String anAddUrl : addUrl) {
                AlgAuthCodeDomain algAuthCodeDomain = new AlgAuthCodeDomain();
                String addSn = UUIDUtil.createUUID();
                algAuthCodeDomain.setAddSn(addSn);
                algAuthCodeDomain.setAcdSn(acdSn);
                algAuthCodeDomain.setAddUrl(anAddUrl);
                Result resultDomain = authCodeDomainService.createAuthCodeDomain(algAuthCodeDomain);
                if(resultDomain.getMsg() != "成功"){
                    return Result.failureResponse();
                }
            }
        }*//*
        Result result=authCodeService.createAuthCode(algAuthCode);
        return result;
    }
*/


    /*@RequestMapping(value = "/deleteauthcodedomain/{addSn}",method = RequestMethod.GET)
    public Result deleteauthcodedomain(@PathVariable("addSn") String addSn){
        Result result = authCodeDomainService.deleteAuthCodeDomain(addSn);
        return  result;
    }*/



}
