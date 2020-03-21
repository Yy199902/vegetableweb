package com.lanou.vegetableweb.result;

import java.util.HashMap;

public class ApiResultUtils {
    private static final int DEFAULT_SUCCESS_CODE = 200;
    private static final String DEFAULT_SUCCESS_MESSAGE = "ok";
    private static final String DEFAULT_ERROR_MESSAGE = "error";



    public static ApiResult ok(Object data) {
        ApiResult k12Result = ok();
        k12Result.setData(data);
        return k12Result;
    }



    public static ApiResult ok() {
        ApiResult k12Result = new ApiResult();
        k12Result.setCode(DEFAULT_SUCCESS_CODE);
        k12Result.setMessage(DEFAULT_SUCCESS_MESSAGE);

        return k12Result;
    }

    public static ApiResult error(int code) {
        ApiResult k12Result = new ApiResult();
        k12Result.setCode(code);
        k12Result.setMessage(DEFAULT_ERROR_MESSAGE);
        return k12Result;
    }

    public static ApiResult error(int code, String message) {
        ApiResult k12Result = new ApiResult();
        k12Result.setCode(code);
        k12Result.setMessage(message);
        return k12Result;
    }
}
