package com.kjtpay.customfund.core.service.manager;

import com.kjtpay.customfund.common.dal.entity.SysLogEntity;
import com.kjtpay.customfund.common.util.entity.PageResult;
import com.kjtpay.customfund.common.util.entity.Query;

/**
 * 系统日志管理服务
 *
 * @Auther yulibin
 * @Date 2018/9/19 15:22
 */
public interface SysLogManager {

    void saveLog(SysLogEntity log);

    /**
     * @Author yulibin
     * @Description 根据条件分页查询
     * @Date 2018/10/8 16:57
     * @Param 
     * @return 
     **/
    PageResult listForPage(Query query);

    int batchRemove(Long[] id);

    int batchRemoveAll();

}
