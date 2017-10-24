package com.beyond.algo.algoconsoleboot.base;

import com.beyond.algo.algoconsoleboot.infra.UserService;
import com.beyond.algo.common.Assert;
import com.beyond.algo.model.AlgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    @Autowired
    private UserService userService;
    public AlgUser getUserInfo() throws Exception{
        SecurityContext ctx = SecurityContextHolder.getContext();
        if(Assert.isEmpty(ctx)) {
            throw new Exception("用户未登录！");
        }
        Authentication auth = ctx.getAuthentication();
        return userService.findByUsrCode(auth.getName());

    }
}
