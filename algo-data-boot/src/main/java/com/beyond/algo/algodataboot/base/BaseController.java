package com.beyond.algo.algodataboot.base;

import com.beyond.algo.algodataboot.infra.UserService;
import com.beyond.algo.common.Assert;
import com.beyond.algo.exception.AlgException;
import com.beyond.algo.model.AlgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    @Autowired
    private UserService userService;
    public AlgUser getUserInfo() throws AlgException {
        SecurityContext ctx = SecurityContextHolder.getContext();
        if(Assert.isEmpty(ctx)) {
            String[] checkMessage = {"未登录",""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000002",checkMessage);
        }
        Authentication auth = ctx.getAuthentication();
        return userService.findByUsrCode(auth.getName());

    }
}
