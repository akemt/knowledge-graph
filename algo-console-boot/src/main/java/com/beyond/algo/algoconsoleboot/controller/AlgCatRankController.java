package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.algoconsoleboot.infra.AlgCatRankService;
import com.beyond.algo.common.Result;
import com.beyond.algo.model.AlgModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XianjieZhang E-mail:xj_zh@foxmail.com
 * @version Created in：2017/10/22 0022 下午 12:12
 */
@RestController
@RequestMapping("/algcatrank")
public class AlgCatRankController {

    @Autowired
    private AlgCatRankService algCatRankService;

    @RequestMapping(value = "/{catname}/{usage}",method = RequestMethod.GET)
    public  List<AlgModule> listAlg(@PathVariable("catname") String catName,@PathVariable("usage") String usage){
        List<AlgModule> result = algCatRankService.listAlg(catName,usage);
        return result;
    }
}
