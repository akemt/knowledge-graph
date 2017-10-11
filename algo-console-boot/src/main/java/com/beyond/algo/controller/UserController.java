package com.beyond.algo.controller;

import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.model.AlgUser;

import com.beyond.algo.infra.UserService;
import com.beyond.algo.model.UserVo;
import com.beyond.algo.model.UserAccountVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;




/**
 * @author ：zhangchuanzhi
 * @Description:实现用户登录注册功能
 * @date ：13:16 2017/9/25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * @author ：zhangchuanzhi
     * @Description:实现用户注册功能
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：13:16 2017/9/25
     */
    @RequestMapping(value="/register", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result<Object> register(AlgUser user){
        logger.info("用户名:{},用户密码:{},用户邮箱:{}", user.getUsrName(), user.getPasswd(),user.getEmail());
        try {
            Result result = userService.createUser(user);
            return result;
        } catch (Exception e) {
            logger.info("注册失败",e);
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
    /**
     * @author ：zhangchuanzhi
     * @Description:用户登录处理
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：8:49 2017/9/27
     */
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value="/login", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result userLogin(AlgUser user) {
        logger.info("用户名:{},用户密码:{}", user.getUsrName(), user.getPasswd());
        try {

            Result result = userService.userLogin(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户修改密码
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：8:49 2017/9/27
     */
   @RequestMapping(value="/changePassword", method=RequestMethod.POST)
    @ResponseBody
    public Result changePassword(UserVo userVo) {
       logger.info("用户唯一值:{},用户密码:{},用户确认密码:{},用户新密码:{}", userVo.getUsrSn(), userVo.getPasswd(), userVo.getConfirmPassword(), userVo.getNewPassword());
        try {
            Result result = userService.changePassword(userVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }


    /**
     * @author ：zhangchuanzhi
     * @Description:用户更新信息
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：9:14 2017/10/10
     */

    @RequestMapping(value="/updateUserInformation", method=RequestMethod.POST)
    @ResponseBody
    public Result updateUserInformation(AlgUser user) {
        logger.info("用户名字:{},用户英文名:{},用户邮箱:{},用户电话:{},用户主页{},用户是短信还是邮箱发送{},用户唯一主键", user.getUsrName(), user.getUsrCode(),user.getEmail(),user.getTelephone(),user.getUsrUrl(),user.getNeedNotify(),user.getUsrSn());
        try {
            Result result = userService.updateUserInformation(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
    /**
     * @author ：zhangchuanzhi
     * @Description:用户更新信息
     * @param：accSn
     * @Modify By :zhangchuanzhi
     * @date ：9:14 2017/10/10
     */

    @RequestMapping(value="/accountInformation", method=RequestMethod.GET)
    @ResponseBody
    public Result accountInformation(String accSn) {

          logger.info("账户主键:{}",accSn);
        UserAccountVo algAccount = null;
        try {
            algAccount = userService.accountInformation(accSn);
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

    /**
     * @author ：zhangchuanzhi
     * @Description:用户充值记录
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：14:07 2017/10/11
     */

/*    @RequestMapping(value="/payRecord", method=RequestMethod.GET)
    @ResponseBody
    public Result payRecord(String accSn) {

        logger.info("账户主键:{}",accSn);
        UserAccountVo algAccount = null;
        try {
            algAccount = userService.accountInformation(accSn);
            if(Assert.isNotNULL(algAccount)){
                return  Result.ok(algAccount);
            }else{
                return Result.failureResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }*/

}
