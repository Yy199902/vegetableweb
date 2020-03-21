package com.lanou.vegetableweb.server;


import com.lanou.vegetableweb.entity.SysRole;
import com.lanou.vegetableweb.entity.SysUser;

import java.util.List;
import java.util.Set;

public interface LoginServer {
    SysUser getUser(SysUser user);

    Set<String> getRole(String userName);
}
