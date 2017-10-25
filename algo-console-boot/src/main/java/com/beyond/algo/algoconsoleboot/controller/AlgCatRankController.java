package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.infra.AlgCatRankService;
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
    public  List<AlgModule> listAlg(String catName,String usage,String modName,short numPage,short numRows){
        //log.info()
        List<AlgModule> result = algCatRankService.listAlg(catName,usage,modName,numPage,numRows);
        return result;
    }
}
