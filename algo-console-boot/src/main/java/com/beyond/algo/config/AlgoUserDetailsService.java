package com.beyond.algo.config;


import com.beyond.algo.common.AESUtil;
import com.beyond.algo.common.Assert;
import com.beyond.algo.infra.UserService;
import com.beyond.algo.model.AlgUser;
import com.beyond.algo.model.ProjectConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by lxg
 * on 2017/2/20.
 */
public class AlgoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectConfigEntity projectConfigEntity;

    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        AlgUser algUser = userService.findByUsername(username);
        if(Assert.isEmpty(algUser) ){
            throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        //**collection添加权限没实现

        String passwordEncryp = AESUtil.decrypt(algUser.getPasswd(),projectConfigEntity.getKeyAES());
        return new org.springframework.security.core.userdetails.User(username,algUser.getPasswd(),collection);
    }
}
