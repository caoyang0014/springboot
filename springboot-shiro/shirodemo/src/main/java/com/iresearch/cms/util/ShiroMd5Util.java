package com.iresearch.cms.util;

import com.iresearch.cms.entity.SysUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author:曹洋
 * @Description：
 * @Date: Create in  2018/9/18 001814:37
 */
public class ShiroMd5Util {

    public static String  SysMd5(SysUser user) {
        String hashAlgorithmName = "MD5";//加密方式

        Object crdentials =user.getPassword();//密码原值

        ByteSource salt = ByteSource.Util.bytes(user.getCredentialsSalt());

        int hashIterations = 2;//加密2次

        SimpleHash hash = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);

        return hash.toString();
    }
}
