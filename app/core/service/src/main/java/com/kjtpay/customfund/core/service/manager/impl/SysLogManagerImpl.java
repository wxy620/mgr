package com.kjtpay.customfund.core.service.manager.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kjtpay.customfund.common.dal.dao.master.SysLogMapper;
import com.kjtpay.customfund.common.dal.entity.SysLogEntity;
import com.kjtpay.customfund.common.util.entity.PageResult;
import com.kjtpay.customfund.common.util.entity.Query;
import com.kjtpay.customfund.core.service.manager.SysLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统日志管理服务实现
 *
 * @Auther yulibin
 * @Date 2018/9/14 15:52
 */
@Component("sysLogManager")
public class SysLogManagerImpl implements SysLogManager {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void saveLog(SysLogEntity log) {
        sysLogMapper.save(log);
    }



    @Override
    public PageResult listForPage(Query query) {
        int pageNum = query.getAsInt("pageNumber");
        int pageSize = query.getAsInt("pageSize");
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<SysLogEntity> sysLogEntityList = sysLogMapper.listForPage(query);
        return new PageResult(page.getTotal(), sysLogEntityList);
    }


//    @Override
//    public List<SysLogEntity> listLog(Query query) {
//        try {
//            Page<SysLogEntity> list=PageHelper.startPage(pageInfo.getPageNo(),pageInfo.getPageSize()).doSelectPage(()->sysLogMapper.listForPage());
//            List<SysLogEntity> sysLogEntityList=sysLogMapper.listForPage(query);
//            PageInfo page=new PageInfo(sysLogEntityList);
//            int n=page.getPageSize();
//            int n2=page.getPages();
//            int n3=page.getSize();
//            long n4=page.getTotal();
//            long m=n4;
//            return list;
//        } catch (Exception ex) {
//            String s1 = ex.getMessage();
//            System.out.print(ex.getMessage());
//            return null;
//        }
//    }


    @Override
    public int batchRemove(Long[] id) {
        return sysLogMapper.batchRemove(id);
    }

    @Override
    public int batchRemoveAll() {
        return sysLogMapper.batchRemoveAll();
    }

}
