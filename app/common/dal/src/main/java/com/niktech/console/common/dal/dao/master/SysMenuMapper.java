package com.niktech.console.common.dal.dao.master;

import com.niktech.console.common.dal.entity.SysMenuEntity;
import com.niktech.console.common.dal.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统菜单dao
 *
 * @Auther yulibin
 * @Date 2018/9/13 20:39
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
	
	List<SysMenuEntity> listParentId(Long parentId);
	
	List<SysMenuEntity> listNotButton();
	
	List<String> listUserPerms(Long userId);
	
	int countMenuChildren(Long parentId);

}
