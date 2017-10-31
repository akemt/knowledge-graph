package com.beyond.algo.algoconsoleboot.controller;

import com.beyond.algo.common.Assert;
import com.beyond.algo.common.Result;
import com.beyond.algo.common.ResultEnum;
import com.beyond.algo.algoconsoleboot.infra.UserService;
import com.beyond.algo.exception.AlgException;
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
    public Result register(AlgUser user) throws AlgException {
        log.info("用户编码:{},用户密码:{},用户邮箱:{}", user.getUsrCode(), user.getPasswd(), user.getEmail());
        userService.createUser(user);
        return Result.successResponse();
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户登录处理
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：8:49 2017/9/27
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result userLogin(AlgUser user)throws AlgException {
        log.info("用户编码:{},用户密码:{}", user.getUsrCode(), user.getPasswd());
        userService.userLogin(user);
        return  Result.successResponse();

    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户修改密码
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：8:49 2017/9/27
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result changePassword(UserVo userVo) throws AlgException{
        log.info("用户唯一值:{},用户密码:{},用户确认密码:{},用户新密码:{}", userVo.getUsrSn(), userVo.getPasswd(), userVo.getConfirmPassword(), userVo.getNewPassword());
        userService.changePassword(userVo);
        return Result.successResponse();
    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户更新信息
     * @param：User
     * @Modify By :zhangchuanzhi
     * @date ：9:14 2017/10/10
     */
    @RequestMapping(value = "/updateUserInformation", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result updateUserInformation(AlgUser user)throws AlgException {
        log.info("用户全名:{},用户编码:{},用户邮箱:{},用户电话:{},用户主页{},用户是短信还是邮箱发送{},用户唯一主键", user.getUsrName(), user.getUsrCode(), user.getEmail(), user.getTelephone(), user.getUsrUrl(), user.getNeedNotify(), user.getUsrSn());
        userService.updateUserInformation(user);
        return Result.successResponse();

    }

    /**
     * @author ：zhangchuanzhi
     * @Description:用户账户信息
     * @param：accSn
     * @Modify By :zhangchuanzhi
     * @date ：9:14 2017/10/10
     */
    @RequestMapping(value = "/accountInformation", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result accountInformation(String accSn) throws AlgException{
        log.info("账户主键:{}", accSn);
        UserAccountVo algAccount = null;
        algAccount = userService.accountInformation(accSn);
        return Result.ok(algAccount);
    }
}
