package com.beyond.algo.controller;

import com.beyond.algo.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.infra.ModelSetService;
import com.beyond.algo.model.AlgModelSet;
import com.beyond.algo.model.AlgModel;
import java.util.List;


/**
 * @author ：huangjinqing
 * @Description:算法商店模型模块controller
 * @date ：19:55 2017/10/17
 */
@RestController
@RequestMapping("/algo_modelset")
public class ModelSetController {


    private final static Logger logger = LoggerFactory.getLogger(ModelSetController.class);

    @Autowired
    private ModelSetService modelSetService;
    /**
     * @author ：huangjinqing
     * @Description:添加模型集
     * @param：
     * @date ：19:56 2017/10/17
     */
    @RequestMapping(value = "/addModelSet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result<Object> addModelSet(AlgModelSet modelSet){
        logger.info("模型集名称:{},用户ID:{}", modelSet.getModelSetName(),modelSet.getUsrSn());
        try {
            Result result = modelSetService.addModelSet(modelSet);
            return result;
        } catch (Exception e) {
            logger.info("添加模型集失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

}
