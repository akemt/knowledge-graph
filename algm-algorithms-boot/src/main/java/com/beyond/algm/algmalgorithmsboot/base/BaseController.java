package com.beyond.algm.algmalgorithmsboot.base;

import com.beyond.algm.algmalgorithmsboot.infra.UserService;
import com.beyond.algm.common.Assert;
import com.beyond.algm.exception.AlgException;
import com.beyond.algm.model.AlgUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录用户
     * @return
     * @throws AlgException
     */
    public AlgUser getUserInfo() throws AlgException {
        SecurityContext ctx = SecurityContextHolder.getContext();
        if(Assert.isEmpty(ctx)) {
            String[] checkMessage = {"未登录",""};
            throw new AlgException("BEYOND.ALG.SSO.COMMON.VALID.0000002",checkMessage);
        }
        Authentication auth = ctx.getAuthentication();
        if(Assert.isNULL(auth)){
            return null;
        }
        return userService.findByUsrCode(auth.getName());
    }
}
