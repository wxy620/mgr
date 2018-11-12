package com.kjtpay.customfund.web.controller;

import com.kjtpay.customfund.common.util.entity.PageResult;
import com.kjtpay.customfund.common.util.entity.Query;
import com.kjtpay.customfund.core.service.manager.SysLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 系统日志
 *
 * @Auther yulibin
 * @Date 2018/9/15 10:39
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends AbstractController {


    @Autowired
    private SysLogManager sysLogManager;

    @RequestMapping("/list")
    public PageResult listLog(@RequestBody Map<String, Object> params) {
        try {
            Query query = new Query(params);
            return sysLogManager.listForPage(query);
        } catch (Exception ex) {
            String s = ex.getMessage();
            System.out.print(ex.getMessage());
            return new PageResult(0, null);
        }
    }




}
