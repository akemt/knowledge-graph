package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.infra.AlgCatRankService;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.model.AlgModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 12:12
 */
@Slf4j
@RestController
@RequestMapping("/algCatRank")
public class AlgCatRankController {

    @Autowired
    private AlgCatRankService algCatRankService;

    @RequestMapping(value = "/listAlg",method = RequestMethod.GET)
    public  Result listAlg(String catName,String usage,String modName,short numPage,short numRows){
        try {
            //log.info()
            List<AlgModule> result = algCatRankService.listAlg(catName, usage, modName, numPage, numRows);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("返回算法排序列表出错");
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code,e.getMessage());
        }
    }
}
