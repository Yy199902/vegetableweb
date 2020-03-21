package com.lanou.vegetableweb.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
