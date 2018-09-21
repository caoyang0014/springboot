package com.iresearch.cms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:曹洋
 * @Description：
 * @Date: Create in  2018/9/15 001515:22
 */
@Controller
@Api(value = "登录controller")
public class HomeController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String index(){
        return"login";
    }

    @ApiOperation(value = "登录接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "username",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "String"),
            @ApiImplicitParam(name = "GifCode",value = "验证码",required = true,dataType = "String"),
            @ApiImplicitParam(name = "rememberMe",value = "记住",required = true,dataType = "boolean")})
    @RequestMapping(value="/submitLogin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(@Param("username") String username,@Param("password") String password,
                                           @Param("gif") String gif, @Param("rememberMe") boolean rememberMe){
        System.out.println(username+"-----"+password);
        String msg = "";
        Map<String, Object> map = new HashMap<>();
        String gifCode = (String)SecurityUtils.getSubject().getSession().getAttribute(GifCodeController.KEY_CAPTCHA);
        if(gifCode==null || !gif.equals(gifCode)){
            map.put("status",201);
            return map;
        }
        try {
            UsernamePasswordToken token =  new UsernamePasswordToken(username, password,rememberMe);
            char[] password1 = token.getPassword();
            String s = password1.toString();

            System.out.println("*******"+s);
            SecurityUtils.getSubject().login(token);
            token.setRememberMe(true);
            map.put("status",200);
        } catch (UnknownAccountException e) {
            msg = "UnknownAccountException -- > 账号不存在：";
            map.put("status",400);
        } catch (IncorrectCredentialsException e){
            msg = "IncorrectCredentialsException -- > 密码不正确：";
            map.put("status",500);
        }catch (Exception exception){
            msg = "else >> "+exception;
            System.out.println("else -- >" + exception);
        }
        return map;
    }

    /**
     * 登录成功跳转路径
     * @return
     */
    @RequestMapping(value = "/index")
    public String loginSucess(){
        return "index";
    }


    /**
     * 退出功能
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        SecurityUtils.getSubject().logout();
        return "login";
    }

    @RequestMapping("/403")
    public String error(){
        return "403";
    }

}
