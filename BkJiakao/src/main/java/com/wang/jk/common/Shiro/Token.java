package com.wang.jk.common.Shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class Token implements AuthenticationToken {

    private final String token;

    public Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
