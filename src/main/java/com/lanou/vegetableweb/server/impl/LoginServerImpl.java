package com.lanou.vegetableweb.server.impl;


import com.lanou.vegetableweb.dao.LoginMapper;
import com.lanou.vegetableweb.entity.SysRole;
import com.lanou.vegetableweb.entity.SysUser;
import com.lanou.vegetableweb.server.LoginServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class LoginServerImpl implements LoginServer {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public SysUser getUser(SysUser user) {
        return loginMapper.getUser(user);
    }

    @Override
    public Set<String> getRole(String userName) {
        return loginMapper.getRole(userName);
    }
}
