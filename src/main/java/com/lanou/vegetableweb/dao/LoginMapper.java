package com.lanou.vegetableweb.dao;

import com.lanou.vegetableweb.entity.SysRole;
import com.lanou.vegetableweb.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface LoginMapper {
    SysUser getUser(@Param("user") SysUser user);

    Set<String> getRole(@Param("userName") String userName);
}
