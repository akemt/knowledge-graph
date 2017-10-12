package com.beyond.algo.controller;

import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.infra.PayCashService;
import com.beyond.algo.model.AlgCashTrans;
import com.beyond.algo.model.PayRecordVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:支付充值
 * @date ：16:00 2017/10/11
 */
@RestController
@RequestMapping("/pay")
public class PayCashController {
    private final static Logger logger = LoggerFactory.getLogger(PayCashController.class);

    @Autowired
    private PayCashService payCashService;
    /**
     * @author ：zhangchuanzhi
     * @Description:用户充值记录
     * @param：PayRecordVo
     * @Modify By :zhangchuanzhi
     * @date ：14:07 2017/10/11
     */
    @RequestMapping(value="/payRecord", method= RequestMethod.POST)
    @ResponseBody
    public Result payRecord(PayRecordVo payRecordVo) {
        logger.info("用户id:{},Page:{},Row:{}",payRecordVo.getUsrSn(),payRecordVo.getPage(),payRecordVo.getRows());
        List<AlgCashTrans> algCashTransList= payCashService.payRecord(payRecordVo);
        if(Assert.isNotEmpty(algCashTransList)){
            return Result.ok(algCashTransList);
        }else{
            return Result.failure(algCashTransList);
        }
    }
}
