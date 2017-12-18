package com.beyond.algm.algmalgorithmsboot.controller;

import com.beyond.algm.algmalgorithmsboot.base.BaseController;
import com.beyond.algm.common.Result;
import com.beyond.algm.algmalgorithmsboot.infra.PayCashService;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgCashTrans;
import com.beyond.algm.model.AlgUser;
import com.beyond.algm.vo.PayRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

/**
 * @author ：zhangchuanzhi
 * @Description:支付充值
 * @date ：16:00 2017/10/11
 */
@RestController
@Slf4j
public class PayCashController extends BaseController {
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
    @RequestMapping(value="/payRecord", method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<PageInfo<AlgCashTrans>> payRecord(PayRecordVo payRecordVo,PageInfo pageInfo) throws AlgException {
        pageInfo.setPageNum(pageInfo.getPageNum()==0?1 : pageInfo.getPageNum());
        pageInfo.setPageSize(pageInfo.getPageSize()==0?10 : pageInfo.getPageSize());
        AlgUser algUser = getUserInfo();
        payRecordVo.setUsrSn(algUser.getUsrSn());
        logger.info("用户id:{},Page:{},Row:{}",payRecordVo.getUsrSn(),payRecordVo.getPage(),payRecordVo.getRows());
        PageInfo<AlgCashTrans> algCashTransList= payCashService.payRecord(payRecordVo, pageInfo);
        return Result.ok(algCashTransList);
    }

    /**
     * @author ：lindw
     * @Description:用户注册，赠送积分
     */
    @RequestMapping(value="/presentcash", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result payRecord(float freeBal) throws AlgException {
        AlgUser algUser = getUserInfo();
        //注册新用户，赠送积分。
        int result = payCashService.presentCash(algUser.getUsrSn(),freeBal);
        if(result == 1){
            return Result.successResponse();
        }else {
            return Result.failure("用户注册，赠送积分");
        }
    }

    /**
     * @author ：lindw
     * @Description:积分充值
     */
    @RequestMapping(value="/recharge", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result recharge(AlgCashTrans algCashTrans) throws AlgException {
        AlgUser algUser = getUserInfo();
        log.info("充值的现金： {} ", algCashTrans.getCashPayAmount());
        payCashService.recharge(algCashTrans,algUser);
        return Result.successResponse();
    }

    /**
     * @author ：zhangchuanzhi
     * @Description: 购买积分
     * @param：PayVo
     * @Modify By :zhangchuanzhi
     * @date ：15:43 2017/10/16
     */
/*    @RequestMapping(value="/buyIntegral", method= RequestMethod.POST)
    @ResponseBody
    public Result payRecord(PayVo payVo) {
        logger.info("用户充值费用:{},兑换利率:{}",payVo.getMoney(),payVo.getRate());
        // 购买积分
        void  payCashService.buyIntegral(payVo);

    }*/
}
