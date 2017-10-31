package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.infra.LicenseService;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.mapper.AlgDicMapper;
import com.beyond.algo.mapper.AlgProgramLangMapper;
import com.beyond.algo.model.AlgDic;
import com.beyond.algo.model.AlgLicense;
import com.beyond.algo.model.AlgProgramLang;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * @author ：lindewei
 * @Description: 算法新增
 */
@Slf4j
@RestController
public class AddAssemblyController {

    @Autowired
    private LicenseService licenseService;
    @Autowired
    private AlgProgramLangMapper algProgramLangMapper;
    @Autowired
    private AlgDicMapper algDicMapper;

     /**
     * @author ：lindewei
     * @Description:协议
     * @param：licSn
     */
    @RequestMapping(value = "/license", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result license(String licSn) {
        try {
            //协议
            List<AlgLicense> algLicense = licenseService.selectAlgLicense(licSn);
            return Result.ok(algLicense);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：lindewei
     * @Description:编程语言
     * @param：licSn
     */
    @RequestMapping(value = "/programLang", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result license() {
        try {
            //编程
            List<AlgProgramLang> algProgramLang =algProgramLangMapper.selectAll();
            return Result.ok(algProgramLang);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：lindewei
     * @Description:集群
     * @param：dicSort 分类
     */
    @RequestMapping(value = "/colony", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result colony(String dicCode,int dicSort) {
        try {
            //集群
            List<AlgDic> algDic =algDicMapper.getDictionarylist(dicCode, dicSort);
            return Result.ok(algDic);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
}
