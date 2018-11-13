package com.niktech.console.web.controller;

import com.niktech.console.common.util.constant.SystemConstant;
import com.niktech.console.common.util.entity.PageResult;
import com.niktech.console.common.util.entity.Query;
import com.niktech.console.common.util.entity.R;
import com.niktech.console.common.util.utils.CommonUtils;
import com.niktech.console.core.model.SysUser;
import com.niktech.console.core.service.manager.SysUserManager;
import com.niktech.console.web.common.annotation.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 系统用户
 *
 * @Auther yulibin
 * @Date 2018/9/15 20:39
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {


    @Autowired
    private SysUserManager sysUserManager;

    /**
     * 用户列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/list")
    public PageResult list(@RequestBody Map<String, Object> params) {
    	try {
			Query query = new Query(params);
			if (getUserId() != SystemConstant.SUPER_ADMIN) {
				params.put("userIdCreate", getUserId());
			}
			return sysUserManager.listUser(query);
		}catch (Exception ex){
    		System.out.println(ex.getMessage());
    		return  null;
		}

    }


    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public R info()
	{
		SysUser user = new SysUser();
		user.setUsername("qt");
        return R.ok().put("user", user);
    }


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @OperateLog("新增用户")
    @RequestMapping("/save")
    public R save(@RequestBody SysUser user) {
        user.setUserIdCreate(getUserId());
        int count = sysUserManager.saveUser(user);
        return CommonUtils.msg(count);
    }


    	/**
	 * 根据id查询详情
	 * @param userId
	 * @return
	 */
	@RequestMapping("/infoUser")
	public R getById(@RequestBody Long userId) {
		return CommonUtils.msg(sysUserManager.getById(userId));
	}


	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@OperateLog("修改用户")
	@RequestMapping("/update")
	public R update(@RequestBody SysUser user) {
        return CommonUtils.msg(sysUserManager.updateUser(user));
	}



	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@OperateLog("删除用户")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
	    return CommonUtils.msg(sysUserManager.batchRemove(id));
	}

	/**
	 * 启用账户
	 * @param id
	 * @return
	 */
	@OperateLog("启用账户")
	@RequestMapping("/enable")
	public R updateUserEnable(@RequestBody Long[] id) {
        return CommonUtils.msg(id, sysUserManager.updateUserEnable(id));
	}

	/**
	 * 禁用账户
	 * @param id
	 * @return
	 */
	@OperateLog("禁用账户")
	@RequestMapping("/disable")
	public R updateUserDisable(@RequestBody Long[] id) {
        return CommonUtils.msg(id, sysUserManager.updateUserDisable(id));
	}
//
	/**
	 * 重置密码
	 * @param user
	 * @return
	 */
	@OperateLog("重置密码")
	@RequestMapping("/reset")
	public R updatePswd(@RequestBody SysUser user) {
        return CommonUtils.msg(sysUserManager.updatePswd(user));
	}
}
