//package com.kjtpay.customfund.web.common.support.oauth2;
//
//import com.kjtpay.customfund.web.common.utils.ShiroUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.stereotype.Component;
//
//
///**
// * 认证
// *
// * @Auther yulibin
// * @Date 2018/9/13 20:39
// **/
//@Component
//public class OAuth2Realm extends AuthorizingRealm {
//
////	@Autowired
////	private SysUserManager sysUserManager;
//
////    @Override
////    public boolean supports(AuthenticationToken token) {
////        return token instanceof OAuth2Token;
////    }
//
//    /**
//     * 授权(验证权限时调用)
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Long userId = ShiroUtils.getUserId();
////		//用户角色
////		Set<String> rolesSet = sysUserManager.listUserRoles(userId);
////		//用户权限
////		Set<String> permsSet = sysUserManager.listUserPerms(userId);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
////		info.setRoles(rolesSet);
////		info.setStringPermissions(permsSet);
//        return info;
//    }
//
//    /**
//     * 认证(登录时调用)
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        String accessToken = (String) token.getPrincipal();
////
////        //根据accessToken，查询用户信息
////        SysUserToken tokenEntity = sysUserManager.getByToken(accessToken);
////        //token失效
////        if(tokenEntity == null || tokenEntity.getGmtExpire().getTime() < System.currentTimeMillis()){
////            throw new IncorrectCredentialsException("token失效，请重新登录");
////        }
////
////        //查询用户信息
////        SysUser user = sysUserManager.getById(tokenEntity.getUserId());
////        //账号锁定
////        if(user.getStatus() == 0){
////            throw new LockedAccountException("账号已被锁定,请联系管理员");
////        }
////
////        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
////        return info;
//        String s=accessToken;
//        return  null;
//    }
//}

package com.kjtpay.customfund.web.common.support.oauth2;

import com.kjtpay.customfund.core.model.SysUser;
import com.kjtpay.customfund.core.service.manager.SysUserManager;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 认证
 *
 * @Auther yulibin
 * @Date 2018/9/13 20:39
 **/
//@Component
public class OAuth2Realm extends AuthorizingRealm {

	@Autowired
	private SysUserManager sysUserManager;

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        SysUser user = sysUserManager.getByUserName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }


    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        SysUser user = sysUserManager.getByUserName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        for (Role role:user.getRoles()) {
//            //添加角色
//            simpleAuthorizationInfo.addRole(role.getRoleName());
//            for (Permission permission:role.getPermissions()) {
//                //添加权限
//                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
//            }
//        }
        return simpleAuthorizationInfo;

    }


}
