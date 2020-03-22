package com.lanou.vegetableweb.web.controller.role;

import com.lanou.vegetableweb.entity.SysUser;
import com.lanou.vegetableweb.enums.ResultEnum;
import com.lanou.vegetableweb.result.ApiResult;
import com.lanou.vegetableweb.result.ApiResultUtils;
import com.lanou.vegetableweb.utils.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/auth")
@ResponseBody
public class Login {
    // 加密的字符串,相当于签名
    private static final String SINGNATURE_TOKEN = "YUYIN";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult submitLogin(String username, String password, HttpServletRequest request) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        SecurityUtils.getSubject().login(token);
        SysUser user = (SysUser) subject.getPrincipal();
        String encryptionKey = DigestUtils.md5DigestAsHex((SINGNATURE_TOKEN + user.getUsername()).getBytes());
        RedisPoolUtil.setEx(encryptionKey.substring(0,6),encryptionKey,1800);
        return ApiResultUtils.ok();


    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @RequestMapping(value = "/role", method = RequestMethod.GET)
    @RequiresPermissions("role")
    public String role() {
        System.out.println("进入");
        return "成功";
    }


}
