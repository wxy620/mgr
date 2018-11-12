package com.kjtpay.customfund.web.controller;

import com.kjtpay.customfund.core.model.SysUser;
import com.kjtpay.customfund.web.common.utils.ShiroUtils;

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
