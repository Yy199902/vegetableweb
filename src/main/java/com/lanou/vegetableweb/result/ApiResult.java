package com.lanou.vegetableweb.result;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.util.Map;

@Data
public class ApiResult implements Serializable {
    private int code;
    private String message;
    private Object data;
    private String token;
    private Map<String, Object> paging;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getPaging() {
        return paging;
    }

    public void setPaging(Map<String, Object> paging) {
        this.paging = paging;
    }

}
