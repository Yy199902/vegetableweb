package com.lanou.vegetableweb.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class SysUser implements Serializable {

    private Integer id = 0;

    private String username;

    private String password;

    private String nickname;

    private String mobile;

    private String email;

    private String qq;

    private LocalTime birthday;

    private int gender;

    private String avatar;

    private String userType;

    private String regIp;

    private String lastLoginIp;

    private LocalTime lastLoginTime;

    private int loginCount;

    private String remark;

    private int status;

    private LocalTime createTime;

    private LocalTime updateTime;
}
