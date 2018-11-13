package com.niktech.console.core.model.convert;


import com.niktech.console.common.dal.entity.SysUserEntity;
import com.niktech.console.common.util.exception.RRException;
import com.niktech.console.core.model.SysUser;

/**
 * @Author yulibin
 * @Description DO对象转换成领域对象
 * @Date 2018/10/9 17:26
 **/
public class ConvertToModel {

    /**
     * SysUserEntity转成领域对象SysUser
     *
     * @return
     */
    public static SysUser toSysUser(SysUserEntity sysUserEntity) {
        SysUser sysUser = new SysUser();
        try {
            sysUser.setEmail(sysUserEntity.getEmail());
            sysUser.setGmtCreate(sysUserEntity.getGmtCreate());
            sysUser.setGmtModified(sysUserEntity.getGmtModified());
            sysUser.setMobile(sysUserEntity.getMobile());
            sysUser.setOrgId(sysUserEntity.getOrgId());
            sysUser.setUsername(sysUserEntity.getUsername());
            sysUser.setOrgName(sysUserEntity.getOrgName());
            sysUser.setPassword(sysUserEntity.getPassword());
            sysUser.setRemark(sysUserEntity.getRemark());
            sysUser.setStatus(sysUserEntity.getStatus());
            sysUser.setRoleIdList(sysUserEntity.getRoleIdList());
            sysUser.setUserId(sysUserEntity.getUserId());
            sysUser.setUserIdCreate(sysUserEntity.getUserIdCreate());
        } catch (Exception e) {
            throw new RRException(e.getMessage());
        }
        return sysUser;
    }

}
