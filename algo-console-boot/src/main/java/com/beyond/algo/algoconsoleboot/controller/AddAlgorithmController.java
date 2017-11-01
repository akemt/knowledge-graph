package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.base.BaseController;
import com.beyond.algo.algoconsoleboot.infra.AddAlgorithmService;
import com.beyond.algo.common.Result;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.AddAlgorithmVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：lindewei
 * @Description: 算法新增
 */
@Slf4j
@RestController
public class AddAlgorithmController extends BaseController {

    @Autowired
    private AddAlgorithmService addAlgorithmService;
     /**
     * @author ：lindewei
     * @Description:算法新增
     * @param：User
     */
    @RequestMapping(value = "/addAlgorithm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result AddAlgorithm(AddAlgorithmVo addAlgorithmVo) throws AlgException {
        AlgUser algUser = getUserInfo();
        addAlgorithmVo.setModName(algUser.getUsrName());
        addAlgorithmService.addAlgorithm(addAlgorithmVo);
        return Result.successResponse();
    }
}
