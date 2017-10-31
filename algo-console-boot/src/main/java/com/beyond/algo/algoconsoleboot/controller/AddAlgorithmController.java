package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.infra.AddAlgorithmService;
import com.beyond.algo.algoconsoleboot.infra.LicenseService;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgLicense;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AddAlgorithmVo;
import com.beyond.algo.vo.AlgFileReadWriteVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
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
public class AddAlgorithmController {

    @Autowired
    private AddAlgorithmService addAlgorithmService;
    @Autowired
    private LicenseService licenseService;
     /**
     * @author ：lindewei
     * @Description:算法新增
     * @param：User
     */
    @RequestMapping(value = "/addAlgorithm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result AddAlgorithm(AddAlgorithmVo addAlgorithmVo) throws AlgException {
        addAlgorithmService.addAlgorithm(addAlgorithmVo);
        return Result.successResponse();
    }

    // 读取文本
    @RequestMapping(value = "/license", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result license(String licSn) {
        try {
            List<AlgLicense> algLicense = licenseService.selectAlgLicense(licSn);
            return Result.ok(algLicense);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
}
