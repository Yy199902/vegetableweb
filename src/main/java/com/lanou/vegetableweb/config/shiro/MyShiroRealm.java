package com.lanou.vegetableweb.config.shiro;


import com.lanou.vegetableweb.config.exception.MyException;
import com.lanou.vegetableweb.entity.SysRole;
import com.lanou.vegetableweb.entity.SysUser;
import com.lanou.vegetableweb.enums.ResultEnum;
import com.lanou.vegetableweb.server.LoginServer;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Resource
    private LoginServer loginServer;


    //如果项目中用到了事物，@Autowired注解会使事物失效，可以自己用get方法获取值


    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     *
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException  {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        SysUser user = new SysUser();
        user.setUsername(name);
        user.setPassword(password);

        SysUser userInfo= loginServer.getUser(user);
            if (userInfo != null) {
            // 用户为禁用状态
            if (userInfo.getStatus() != 1) {
                throw new DisabledAccountException("账号已禁用！");
            }
            logger.info("---------------- Shiro 凭证认证成功 ----------------------");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    userInfo, //用户
                    userInfo.getPassword(), //密码
                    getName()  //realm name
            );

                Session session = SecurityUtils.getSubject().getSession();
                session.setAttribute("username", userInfo.getUsername());
            return authenticationInfo;
        }
            logger.info("抛出异常");
        throw new AccountException("账号不存在！");


    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String userName = principals.getPrimaryPrincipal().toString().split(":")[0];
        Set<String> role=loginServer.getRole(userName);
        info.setStringPermissions(role);
        return info;



    }

}
