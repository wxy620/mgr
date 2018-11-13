package com.niktech.console.common.dal.dao.master;

import com.niktech.console.common.dal.entity.SysUserEntity;
import com.niktech.console.common.dal.dao.BaseMapper;
import com.niktech.console.common.util.entity.Query;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户dao
 *
 * @Auther yulibin
 * @Date 2018/9/13 20:39
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

	SysUserEntity getByUserName(String username);
	
	List<Long> listAllMenuId(Long userId);
	
	List<Long> listAllOrgId(Long userId);
	
	int updatePswdByUser(Query query);
	
	int updateUserStatus(Query query);
	
	int updatePswd(SysUserEntity user);
	
}
