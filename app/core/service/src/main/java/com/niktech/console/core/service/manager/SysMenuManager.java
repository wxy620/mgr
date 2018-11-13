package com.niktech.console.core.service.manager;

import com.niktech.console.common.dal.entity.SysMenuEntity;

import java.util.List;

/**
 * 系统菜单
 *
 * @Auther yulibin
 * @Date 2018/9/13 20:39
 */
public interface SysMenuManager {
	

	
	List<SysMenuEntity> listParentId(Long parentId, List<Long> menuIdList);

	
}
