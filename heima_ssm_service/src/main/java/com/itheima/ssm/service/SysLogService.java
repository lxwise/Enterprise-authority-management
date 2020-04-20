package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

/**
 * @author l-xin
 * @create 2020-03-28 15:45
 */
public interface SysLogService {



    public void save(SysLog sysLog);

    List<SysLog> findAll();
}
