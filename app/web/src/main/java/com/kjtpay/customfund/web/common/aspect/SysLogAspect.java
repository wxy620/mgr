package com.kjtpay.customfund.web.common.aspect;

import com.alibaba.fastjson.JSON;
import com.kjtpay.customfund.common.dal.entity.SysLogEntity;
import com.kjtpay.customfund.common.util.utils.CommonUtils;
import com.kjtpay.customfund.core.model.SysUser;
import com.kjtpay.customfund.core.service.manager.SysLogManager;
import com.kjtpay.customfund.web.common.annotation.OperateLog;
import com.kjtpay.customfund.web.common.utils.HttpContextUtils;
import com.kjtpay.customfund.web.common.utils.IpUtils;
import com.kjtpay.customfund.web.common.utils.ShiroUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * 系统日志，切面处理类
 *
 * @Auther yulibin
 * @Date 2018/9/27 11:39
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogManager sysLogManager;
	
	@Pointcut("@annotation(com.kjtpay.customfund.web.common.annotation.OperateLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//保存日志
		saveSysLog(point, time);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLogEntity sysLog = new SysLogEntity();
		OperateLog syslog = method.getAnnotation(OperateLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}
		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params =JSON.toJSONString(args[0]);
			sysLog.setParams(params);
		}catch (Exception e){

		}
		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		sysLog.setIp(IpUtils.getIpAddr(request));
		//用户
		SysUser currUser = ShiroUtils.getUser();
		if(CommonUtils.isNullOrEmpty(currUser)) {
			if(CommonUtils.isNullOrEmpty(sysLog.getParams())) {
				sysLog.setUserId(-1L);
				sysLog.setUsername(sysLog.getParams());
			} else {
				sysLog.setUserId(-1L);
				sysLog.setUsername("获取用户信息为空");
			}
		} else {
			sysLog.setUserId(ShiroUtils.getUserId());
			sysLog.setUsername(ShiroUtils.getUser().getUsername());
		}
		sysLog.setTime(time);
		//保存系统日志
		sysLogManager.saveLog(sysLog);
	}
	
}
