package com.iresearch.cms.controller;

import com.iresearch.cms.util.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @Author:曹洋
 * @Description：
 * @Date: Create in 2018/9/20 0020 16:37
 */
@Controller
@RequestMapping(value = "gif")
public class GifCodeController {
    public static final String KEY_CAPTCHA = "KEY_CAPTCHA";

    @RequestMapping(value="/getGifCode",method= RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");

            HttpSession session = request.getSession();
            CaptchaUtil captchaUtil = new CaptchaUtil();
            StringBuffer stringBuffer = new StringBuffer();
            BufferedImage image = captchaUtil.genRandomCodeImage(stringBuffer);
            session.removeAttribute(KEY_CAPTCHA);
            session.setAttribute(KEY_CAPTCHA,stringBuffer.toString());

            ImageIO.write(image, "JPEG",response.getOutputStream());


        } catch (Exception e) {
            System.err.println("获取验证码异常："+e.getMessage());
        }
    }

}
