package com.wang.jk.common.Shiro;

import com.wang.jk.common.cache.Caches;
import com.wang.jk.common.util.JsonVos;
import com.wang.jk.pojo.result.CodeMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
@Slf4j
public class TokenFilter extends AccessControlFilter {

    private static final String HEAD_TOKEN="Token";
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        log.debug("uri="+((HttpServletRequest)servletRequest).getRequestURI());
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token=request.getHeader(HEAD_TOKEN);
        if(token==null) JsonVos.raise(CodeMsg.EMPTY_ACCESS_TOKEN);

        if(Caches.get(token)==null)JsonVos.raise(CodeMsg.TOKEN_EXPIRED);


        SecurityUtils.getSubject().login(new Token(token));

        return true;
    }
}
