package com.iresearch.cms.mapper;

import com.iresearch.cms.entity.SysPermission;
import com.iresearch.cms.entity.SysRole;
import com.iresearch.cms.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:曹洋
 * @Description：
 * @Date: Create in  2018/9/15 001515:32
 */
@Repository
public interface UserInfoMapper {
    SysUser findByUsername(String username);

    List<SysRole> getAllRole(Integer uid);

    List<SysPermission> getAllPermission(Integer id);

    boolean add(SysUser user);

    List<SysUser> getUserList();
}
