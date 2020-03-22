package com.lanou.vegetableweb.config.shiro;

import com.lanou.vegetableweb.entity.SysUser;
import com.lanou.vegetableweb.utils.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TokenFilter extends AuthenticationFilter {

    //加密的字符串,相当于签名
    private static final String SINGNATURE_TOKEN = "YUYIN";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //这里只有返回false才会执行onAccessDenied方法,因为
        // return super.isAccessAllowed(request, response, mappedValue);
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        String token = getRequestToken((HttpServletRequest) request);
        String login = ((HttpServletRequest) request).getServletPath();

        //如果为登录,就放行
        if ("/auth/login".equals(login)){
            return true;
        }
        if (StringUtils.isBlank(token)){
            System.out.println("没有token");
            return false;
        }

        String newToken= RedisPoolUtil.get(token.substring(0,6));
        if (token.equals(newToken)){
            RedisPoolUtil.setEx(token.substring(0,6),token,1800);
            System.out.println("放行");
            return  true;
        }else{
            System.out.println("token失效");
            return false;
        }

    }
    private String getRequestToken(HttpServletRequest request) {
        //默认从请求头中获得token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        return token;
    }
}
