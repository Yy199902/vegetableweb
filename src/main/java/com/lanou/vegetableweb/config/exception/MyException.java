package com.lanou.vegetableweb.config.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyException extends RuntimeException  {
    private Integer code;

    public MyException( int code, String message ) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
