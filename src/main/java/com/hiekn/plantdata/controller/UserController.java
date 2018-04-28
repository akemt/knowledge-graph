package com.hiekn.plantdata.controller;


import com.hiekn.plantdata.Entity.User;
import com.hiekn.plantdata.common.Result;
import com.hiekn.plantdata.common.UUIDUtil;
import com.hiekn.plantdata.common.WebSecurityConfig;
import com.hiekn.plantdata.exception.GraphException;
import com.hiekn.plantdata.infra.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制层
 */
@RestController
@RequestMapping("user")
public class UserController {



    @Autowired
    private UserService userService;


    /**
     * 获得用户信息
     */
    @GetMapping(value = "")
    @ResponseBody
    public Result getUserInfo(HttpSession session){

        String userName = (String)session.getAttribute(WebSecurityConfig.SESSION_KEY);

        User user = userService.getUserInfo(userName);
        Map<String,String> map = new HashMap<String,String>();
        map.put("role","user");
        map.put("userName",user.getUsrName());
        map.put("userPassWord",user.getUsrPassword());

        return Result.success(map,200,"获取用户信息成功!");
    }


    /**
     *
     * 用户登录
     * @return
     * @throws GraphException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result getUserLogin(HttpSession session,@RequestParam(value = "username") String userName,@RequestParam(value = "password") String password){
        User user = new User();
        user.setUsrName(userName);
        user.setUsrPassword(password);

        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, userName);

        return userService.userLogin(user,session);
    }


    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    @ResponseBody
    public Result logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return Result.success("",200,"登出成功!");
    }
}
