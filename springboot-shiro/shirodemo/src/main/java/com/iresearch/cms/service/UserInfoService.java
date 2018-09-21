package com.iresearch.cms.service;


import com.iresearch.cms.entity.SysPermission;
import com.iresearch.cms.entity.SysRole;
import com.iresearch.cms.entity.SysUser;

import java.util.List;

public interface UserInfoService {
    /**
     * 通过username查找用户信息
     * @param username
     * @return
     */
    public SysUser findByUsername(String username);

    /**
     * 通过uid获取所有的角色信息
     * @param uid
     * @return
     */
    List<SysRole> getAllRole(Integer uid);

    /**
     * 通过角色id获取所有的权限信息
     * @param id
     * @return
     */
    List<SysPermission> getAllPermission(Integer id);

    boolean add(SysUser user);

    List<SysUser> getUserList();
}