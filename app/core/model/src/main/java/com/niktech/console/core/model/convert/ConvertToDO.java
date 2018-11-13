package com.niktech.console.core.model.convert;

import com.niktech.console.common.dal.entity.SysUserEntity;
import com.niktech.console.common.util.exception.RRException;
import com.niktech.console.core.model.SysUser;

/**
 * @Author yulibin
 * @Description 领域对象转换成DO对象
 * @Date 2018/10/9 17:27
 **/
public class ConvertToDO {

    /**
     * SysUserEntity转成领域对象SysUser
     *
     * @return
     */
    public static SysUserEntity toSysUserEntity(SysUser sysUser) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        try {
            sysUserEntity.setEmail(sysUser.getEmail());
            sysUserEntity.setGmtCreate(sysUser.getGmtCreate());
            sysUserEntity.setGmtModified(sysUser.getGmtModified());
            sysUserEntity.setMobile(sysUser.getMobile());
            sysUserEntity.setOrgId(sysUser.getOrgId());
            sysUserEntity.setUsername(sysUser.getUsername());
            sysUserEntity.setOrgName(sysUser.getOrgName());
            sysUserEntity.setPassword(sysUser.getPassword());
            sysUserEntity.setRemark(sysUser.getRemark());
            sysUserEntity.setStatus(sysUser.getStatus());
            sysUserEntity.setRoleIdList(sysUser.getRoleIdList());
            sysUserEntity.setUserId(sysUser.getUserId());
            sysUserEntity.setUserIdCreate(sysUser.getUserIdCreate());
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
        return sysUserEntity;
    }

}
