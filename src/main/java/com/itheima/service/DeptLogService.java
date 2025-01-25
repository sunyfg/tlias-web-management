package com.itheima.service;

import com.itheima.pojo.DeptLog;

public interface DeptLogService {

    /**
     * 记录部门插入日志
     * @param deptLog
     */
    public void insert(DeptLog deptLog);
}
