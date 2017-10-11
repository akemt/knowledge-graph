package com.beyond.algo.controller;

import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.model.UserAccountVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zhangchuanzhi
 * @Description:支付充值
 * @date ：16:00 2017/10/11
 */
@RestController
@RequestMapping("/pay")
public class PayCashController {

    /**
     * @author ：zhangchuanzhi
     * @Description:用户充值记录
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：14:07 2017/10/11
     */

    @RequestMapping(value="/payRecord", method= RequestMethod.GET)
    @ResponseBody
    public Result payRecord(String accSn) {

        logger.info("账户主键:{}",accSn);
        UserAccountVo algAccount = null;
        try {
            algAccount = userService.payRecord(accSn);
            if(Assert.isNotNULL(algAccount)){
                return  Result.ok(algAccount);
            }else{
                return Result.failureResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
}
