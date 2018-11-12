package com.kjtpay.customfund.web.controller;

import com.alibaba.fastjson.JSON;
import com.kjtpay.customfund.common.dal.dao.master.SysMenuMapper;
import com.kjtpay.customfund.common.dal.entity.SysMenuEntity;
import com.kjtpay.customfund.common.util.constant.SystemConstant;
import com.kjtpay.customfund.common.util.entity.R;
import com.kjtpay.customfund.web.common.annotation.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统菜单controller
 *
 * @Auther yulibin
 * @Date 2018/9/13 21:19
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    /**
     * 用户菜单
     *
     * @return
     */
    //@OperateLog("查询用户菜单")
    @RequestMapping("/user")
    @ResponseBody
    public R user() {
        try {


//查询根菜单列表
            long userId = 1;
            List<SysMenuEntity> menuList = new ArrayList<>();

//            List<Long> menuIdList = sysUserMapper.listAllMenuId(userId);
//            menuList = getAllMenuList(menuIdList);

            SysMenuEntity sysMenuEntity1 = new SysMenuEntity();
            sysMenuEntity1.setIcon("fa fa-coffee");
            sysMenuEntity1.setMenuId(1L);
            sysMenuEntity1.setName("系统管理");
            sysMenuEntity1.setOrderNum(0);
            sysMenuEntity1.setParentId(0L);
            sysMenuEntity1.setType(0);

            SysMenuEntity sysMenuEntity11 = new SysMenuEntity();
            sysMenuEntity11.setType(1);
            sysMenuEntity11.setIcon("fa fa-warning");
            sysMenuEntity11.setMenuId(27L);
            sysMenuEntity11.setName("系统日志");
            sysMenuEntity11.setOrderNum(3);
            sysMenuEntity11.setParentId(1L);
            sysMenuEntity11.setType(1);
            sysMenuEntity11.setUrl("base/log/list.html");
            SysMenuEntity sysMenuEntity12 = new SysMenuEntity();
            sysMenuEntity12.setType(1);
            sysMenuEntity12.setIcon("fa fa-bug");
            sysMenuEntity12.setMenuId(67L);
            sysMenuEntity12.setName("系统监控");
            sysMenuEntity12.setOrderNum(6);
            sysMenuEntity12.setParentId(1L);
            sysMenuEntity12.setType(1);
            sysMenuEntity12.setUrl("druid/index.html");
            List<SysMenuEntity> sysMenuEntityList1 = new ArrayList<SysMenuEntity>() {{
                add(sysMenuEntity11);
                add(sysMenuEntity12);
            }};
//            sysMenuEntityList1.add(sysMenuEntity11);
//            sysMenuEntityList1.add(sysMenuEntity12);
            sysMenuEntity1.setList(sysMenuEntityList1);


            SysMenuEntity sysMenuEntity2 = new SysMenuEntity();
            sysMenuEntity2.setIcon("fa fa-desktop");
            sysMenuEntity2.setMenuId(3L);
            sysMenuEntity2.setName("组织机构");
            sysMenuEntity2.setOrderNum(1);
            sysMenuEntity2.setParentId(0L);
            sysMenuEntity2.setType(0);

            SysMenuEntity sysMenuEntity21 = new SysMenuEntity();
            sysMenuEntity21.setType(1);
            sysMenuEntity21.setIcon("fa fa-user");
            sysMenuEntity21.setMenuId(6L);
            sysMenuEntity21.setName("用户管理");
            sysMenuEntity21.setOrderNum(2);
            sysMenuEntity21.setParentId(3L);
            sysMenuEntity21.setType(1);
            sysMenuEntity21.setUrl("base/user/list.html");

//            sysMenuEntityList2.add(sysMenuEntity21);

            List<SysMenuEntity> sysMenuEntityList2 = new ArrayList<SysMenuEntity>() {{
                add(sysMenuEntity21);
            }};

            sysMenuEntity2.setList(sysMenuEntityList2);

//            menuList.add(sysMenuEntity1);
//            menuList.add(sysMenuEntity2);
            menuList = new ArrayList<SysMenuEntity>() {{
                add(sysMenuEntity1);
                add(sysMenuEntity2);
            }};
            String menuListStr = JSON.toJSONString(menuList);
            String s = menuListStr;
            System.out.println(s);
            return R.ok().put("menuList", menuList);

        } catch (Exception ex) {
            return R.error(ex.getMessage());
        }
    }

    /**
     * 获取所有菜单列表
     */
    @OperateLog("获取所有菜单列表")
    private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenuEntity> menuList = listParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList) {
        List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

        for (SysMenuEntity entity : menuList) {
            if (entity.getType() == SystemConstant.MenuType.CATALOG.getValue()) {//目录
                entity.setList(getMenuTreeList(listParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }

    public List<SysMenuEntity> listParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuEntity> menuList = sysMenuMapper.listParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuEntity> userMenuList = new ArrayList<>();
        for (SysMenuEntity menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }


}
