package com.iresearch.cms.service.impl;

import com.iresearch.cms.entity.SysPermission;
import com.iresearch.cms.entity.SysRole;
import com.iresearch.cms.entity.SysUser;
import com.iresearch.cms.mapper.UserInfoMapper;
import com.iresearch.cms.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:曹洋
 * @Description：
 * @Date: Create in  2018/9/15 001515:31
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public SysUser findByUsername(String username) {
        return userInfoMapper.findByUsername(username);
    }

    @Override
    public List<SysRole> getAllRole(Integer uid) {
        return userInfoMapper.getAllRole(uid);
    }

    @Override
    public List<SysPermission> getAllPermission(Integer id) {
        return userInfoMapper.getAllPermission(id);
    }

    @Override
    public boolean add(SysUser user) {
        return userInfoMapper.add(user);
    }

    @Override
    public List<SysUser> getUserList() {
        return userInfoMapper.getUserList();
    }
}
