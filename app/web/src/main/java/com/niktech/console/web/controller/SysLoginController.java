package com.niktech.console.web.controller;

import com.niktech.console.web.common.annotation.OperateLog;
import com.niktech.console.web.common.support.oauth2.TokenGenerator;
import com.niktech.console.common.util.entity.R;
import com.niktech.console.common.util.utils.MD5Utils;
import com.niktech.console.core.service.manager.SysUserManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

/**
 * 用户controller
 *
 * @Auther yulibin
 * @Date 2018/9/13 20:39
 */
@RestController
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {
    /**
     * token过期时间，12小时
     */
    private final static int TOKEN_EXPIRE = 1000 * 60 * 60 * 12;

    @Autowired
    private SysUserManager sysUserManager;

    /**
     * 登录
     */
    @OperateLog("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public R login(String username, String password) throws IOException {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                MD5Utils.encrypt(username, password));

        //进行验证，这里可以捕获异常，然后返回对应信息
        subject.login(usernamePasswordToken);
        //生成token
        String token = TokenGenerator.generateValue();
        //当前时间
        Date now = new Date();
        Date gmtExpire = new Date(now.getTime() + TOKEN_EXPIRE);
        R r = R.ok().put("token", token).put("expire", gmtExpire);
        return r;
    }


}
