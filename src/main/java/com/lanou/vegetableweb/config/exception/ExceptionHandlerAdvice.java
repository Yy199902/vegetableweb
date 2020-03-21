package com.lanou.vegetableweb.config.exception;


import com.lanou.vegetableweb.enums.ResultEnum;
import com.lanou.vegetableweb.result.ApiResult;
import com.lanou.vegetableweb.result.ApiResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
@RestController
public class ExceptionHandlerAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MyException.class)
    @CrossOrigin
    public ApiResult myException(MyException ex) {
        logger.warn(ex.getMessage());
       return ApiResultUtils.error(ex.getCode(),ex.getMessage());
    }

    @ExceptionHandler(AccountException.class)
    @CrossOrigin
    public ApiResult authorizationException(AccountException ex) {
        return ApiResultUtils.error(ResultEnum.PASSWORDERROR.getCode(),ResultEnum.PASSWORDERROR.getMsg());
    }

    @ExceptionHandler(DisabledAccountException.class)
    @CrossOrigin
    public ApiResult DisabledAccountException(DisabledAccountException ex) {

        return ApiResultUtils.error(ResultEnum.PROHIBIT.getCode(),ResultEnum.PROHIBIT.getMsg());
    }
    @ExceptionHandler(AuthorizationException.class)
    @CrossOrigin
    public String authorizationException(AuthorizationException ex) {
        if (ex instanceof UnauthenticatedException) {
            return "token错误或未登录";
        } else if (ex instanceof UnauthorizedException) {
            return "用户无权限";
        } else {
            return ex.getMessage();
        }
    }
}
