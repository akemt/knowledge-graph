package com.beyond.algo.controller;

import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.infra.UserService;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.vo.UserAccountVo;
import com.beyond.algo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：zhangchuanzhi
 * @Description:实现用户登录注册功能
 * @date ：13:16 2017/9/25
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author ：zhangchuanzhi
     * @Description:实现用户注册功能
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：13:16 2017/9/25
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Result<Object> register(AlgUser user) {
        log.info("用户编码:{},用户密码:{},用户邮箱:{}", user.getUsrCode(), user.getPasswd(), user.getEmail());
        try {
            Result result = userService.createUser(user);
            return result;
        } catch (Exception e) {
            log.info("注册失败", e);
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户登录处理
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：8:49 2017/9/27
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result userLogin(AlgUser user) {
        log.info("用户编码:{},用户密码:{}", user.getUsrCode(), user.getPasswd());
        try {

            Result result = userService.userLogin(user);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户修改密码
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：8:49 2017/9/27
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public Result changePassword(UserVo userVo) {
        log.info("用户唯一值:{},用户密码:{},用户确认密码:{},用户新密码:{}", userVo.getUsrSn(), userVo.getPasswd(), userVo.getConfirmPassword(), userVo.getNewPassword());
        try {
            Result result = userService.changePassword(userVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户更新信息
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：9:14 2017/10/10
     */
    @RequestMapping(value = "/updateUserInformation", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUserInformation(AlgUser user) {
        log.info("用户全名:{},用户编码:{},用户邮箱:{},用户电话:{},用户主页{},用户是短信还是邮箱发送{},用户唯一主键", user.getUsrName(), user.getUsrCode(), user.getEmail(), user.getTelephone(), user.getUsrUrl(), user.getNeedNotify(), user.getUsrSn());
        try {
            Result result = userService.updateUserInformation(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户账户信息
     * @param：accSn
     * @Modify By :zhangchuanzhi
     * @date ：9:14 2017/10/10
     */
    @RequestMapping(value = "/accountInformation", method = RequestMethod.GET)
    @ResponseBody
    public Result accountInformation(String accSn) {

        log.info("账户主键:{}", accSn);
        UserAccountVo algAccount = null;
        try {
            algAccount = userService.accountInformation(accSn);
            if (Assert.isNotNULL(algAccount)) {
                return Result.ok(algAccount);
            } else {
                return Result.failureResponse();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.FAILURE.code, e.getMessage());
        }
    }
}
