package com.kjtpay.customfund.core.service.manager.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kjtpay.customfund.common.dal.dao.master.*;
import com.kjtpay.customfund.common.dal.entity.SysUserEntity;
import com.kjtpay.customfund.common.util.constant.SystemConstant;
//import com.kjtpay.customfund.common.util.entity.Page;
import com.kjtpay.customfund.common.util.entity.PageResult;
import com.kjtpay.customfund.common.util.entity.Query;
import com.kjtpay.customfund.common.util.utils.MD5Utils;
import com.kjtpay.customfund.core.model.SysUser;
import com.kjtpay.customfund.core.model.convert.ConvertToDO;
import com.kjtpay.customfund.core.model.convert.ConvertToModel;
import com.kjtpay.customfund.core.service.manager.SysUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 系统用户管理服务实现
 *
 * @Auther yulibin
 * @Date 2018/9/19 15:22
 */
@Component("sysUserManager")
public class SysUserManagerImpl implements SysUserManager {

	/**
	 * token过期时间，12小时
	 */
	private final static int TOKEN_EXPIRE = 1000 * 60 * 60 * 12;
	
	@Autowired
	private SysUserMapper sysUserMapper;

	
	@Override
	public PageResult listUser(Query search) {
		int pageNum = search.getAsInt("pageNumber");
		int pageSize = search.getAsInt("pageSize");
		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<SysUserEntity> sysUserEntityList = sysUserMapper.listForPage(search);
		List<SysUser> sysUserList=new ArrayList<>();
		for (SysUserEntity sysUserEntity:sysUserEntityList) {
			sysUserList.add(ConvertToModel.toSysUser(sysUserEntity));
		}
		return new PageResult(page.getTotal(), sysUserList);
	}

	@Override
	public SysUser getByUserName(String username) {
		return ConvertToModel.toSysUser(sysUserMapper.getByUserName(username));
	}

	@Override
	public int saveUser(SysUser user) {
		SysUserEntity sysUserEntity=ConvertToDO.toSysUserEntity(user);
		int count = sysUserMapper.save(sysUserEntity);
		return count;
	}

	@Override
	public SysUser getById(Long userId) {
		SysUserEntity user = sysUserMapper.getObjectById(userId);
		return ConvertToModel.toSysUser(user);
	}

	@Override
	public int updateUser(SysUser user) {
		int count = sysUserMapper.update(ConvertToDO.toSysUserEntity(user));
		return count;
	}

	@Override
	public int batchRemove(Long[] id) {
		int count = sysUserMapper.batchRemove(id);
		return count;
	}




	@Override
	public int updateUserEnable(Long[] id) {
		Query query = new Query();
		query.put("status", SystemConstant.StatusType.ENABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return count;
	}

	@Override
	public int updateUserDisable(Long[] id) {
		Query query = new Query();
		query.put("status", SystemConstant.StatusType.DISABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return count;
	}

	@Override
	public int updatePswd(SysUser user) {
		SysUserEntity currUser = sysUserMapper.getObjectById(user.getUserId());
		currUser.setPassword(MD5Utils.encrypt(currUser.getUsername(), user.getPassword()));
		return sysUserMapper.updatePswd(currUser);
	}

	@Override
	public SysUserEntity getUserById(Long userId) {//不包含角色信息
		return sysUserMapper.getObjectById(userId);
	}


}
