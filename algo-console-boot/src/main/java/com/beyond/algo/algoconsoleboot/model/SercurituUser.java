package com.beyond.algo.algoconsoleboot.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SercurituUser extends User {
    private String enUserName;
    public SercurituUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    public SercurituUser(String username, String password, Collection<? extends GrantedAuthority> authorities, String enUserName) {
        super(username, password, authorities);
        this.enUserName = enUserName;
    }
}
