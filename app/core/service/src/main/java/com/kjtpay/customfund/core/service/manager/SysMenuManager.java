package com.kjtpay.customfund.core.service.manager;

import com.kjtpay.customfund.common.dal.entity.SysMenuEntity;
import com.kjtpay.customfund.common.util.entity.Query;

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
