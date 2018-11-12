package com.kjtpay.customfund.common.dal.dao.master;

import com.kjtpay.customfund.common.dal.entity.SysLogEntity;
import com.kjtpay.customfund.common.dal.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志 
 *
 * @Auther yulibin
 * @Date 2018/9/13 20:39
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLogEntity> {

	int batchRemoveAll();
	
}
