package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.base.BaseController;
import com.beyond.algo.algoconsoleboot.infra.LicenseService;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.mapper.AlgDicMapper;
import com.beyond.algo.mapper.AlgLicenseMapper;
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


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：lindewei
 * @Description: 算法新增
 */
@Slf4j
@RestController
public class AddAssemblyController extends BaseController {

    @Autowired
    private LicenseService licenseService;
    @Autowired
    private AlgProgramLangMapper algProgramLangMapper;
    @Autowired
    private AlgDicMapper algDicMapper;
    @Autowired
    private AlgLicenseMapper algLicenseMapper;

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
            List<AlgDic> algDic =algDicMapper.getDictionarylist(dicCode);
            return Result.ok(algDic);
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
    @RequestMapping(value = "/initAddAssembly", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result init(String dicCode) {
        try {
            //初始化
            Map<String,List> map=new HashMap<String,List>();
            //编程语言
            List<AlgProgramLang> algProgramLang =algProgramLangMapper.selectAll();
            map.put("algProgramLang",algProgramLang);
            //协议
            List<AlgLicense> license = algLicenseMapper.selectAll();
            map.put("license",license);
            //集群
            String moduleAccessMode="module_access_mode";
            List<AlgDic> AccessandCall =algDicMapper.getDictionarylist(moduleAccessMode);
            map.put("moduleAccessMode",AccessandCall);
            String runEnv="run_env";
            List<AlgDic> RunandEnv =algDicMapper.getDictionarylist(runEnv);
            map.put("runEnv",RunandEnv);
            String iscolony="is_colony";
            List<AlgDic> SingandClu =algDicMapper.getDictionarylist(iscolony);
            map.put("iscolony",SingandClu);
            String standenv="stand_env";
            List<AlgDic> Single =algDicMapper.getDictionarylist(standenv);
            map.put("standenv",Single);
            String gpuenv="gpu_env";
            List<AlgDic> Cluster =algDicMapper.getDictionarylist(gpuenv);
            map.put("gpuenv",Cluster);
            return Result.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
}
