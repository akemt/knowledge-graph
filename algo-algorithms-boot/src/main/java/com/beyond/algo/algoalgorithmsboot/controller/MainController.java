package com.beyond.algo.algoalgorithmsboot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;


@RestController
@EnableAutoConfiguration
public class MainController {

    @ResponseBody
    @RequestMapping(value = "/main", method = {RequestMethod.GET})
    public String index(String jsonStr) throws Exception {

        return jsonStr;
    }
}
