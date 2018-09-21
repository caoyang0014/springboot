package com.iresearch.cms.controller;

import com.iresearch.cms.entity.SysUser;
import com.iresearch.cms.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:曹洋
 * @Description：
 * @Date: Create in 2018/9/20 002010:49
 */
@RestController
@RequestMapping(value = "/swagger")
@Api(value = "SwaggerController")
public class SwaggerController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "获取用户列表",notes = "获取用户列表")
    @RequiresPermissions(value = "swagger:view")
    @RequestMapping(value = "/getUserList",method = RequestMethod.GET)
    public List<SysUser> getUserList(){

        List<SysUser> list = userInfoService.getUserList();
        return list;
    }

}
