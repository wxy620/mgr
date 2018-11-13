package com.niktech.console.web.controller;

import com.niktech.console.web.common.utils.ShiroUtils;
import com.niktech.console.core.model.SysUser;

/**
 * Controller公共组件
 *
 * @Auther yulibin
 * @Date 2018/9/13 20:39
 **/
public class AbstractController {

    protected SysUser getUser() {
        return ShiroUtils.getUser();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
}
