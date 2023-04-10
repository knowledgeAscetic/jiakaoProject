package com.wang.jk.common.Shiro;

import com.wang.jk.common.cache.Caches;
import com.wang.jk.pojo.dto.SysUserDto;
import com.wang.jk.service.SysRoleResourceService;
import com.wang.jk.service.SysUserRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenRealm extends AuthorizingRealm {


    @Autowired
    private SysUserRoleService userRoleService;
    @Autowired
    private SysRoleResourceService roleResourceService;
    public TokenRealm(TokenMatcher matcher)
    {
        super(matcher);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof Token;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        SysUserDto user=Caches.get(token);
        simpleAuthorizationInfo.addStringPermissions(user.getResources());
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String credentials = (String) token.getCredentials();
        return new SimpleAuthenticationInfo(credentials,credentials,getName());
    }
}
