package com.niktech.console.core.service.manager;

import com.niktech.console.common.dal.entity.SysUserEntity;
import com.niktech.console.common.util.entity.PageResult;
import com.niktech.console.common.util.entity.Query;
import com.niktech.console.core.model.SysUser;

/**
 * 系统用户
 *
 * @author yulibin
 * @date 2018年8月11日 上午11:43:01
 */
public interface SysUserManager {

	SysUser getByUserName(String username);

	PageResult listUser(Query search);
	
	int saveUser(SysUser user);
	
	SysUser getById(Long userId);
	
	int updateUser(SysUser user);
	
	int batchRemove(Long[] id);

	
	int updateUserEnable(Long[] id);
	
	int updateUserDisable(Long[] id);
	
	int updatePswd(SysUser user);
	
	SysUserEntity getUserById(Long userId);

	
}
