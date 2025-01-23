package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 查询全部部门数据
     * @return
     */
    List<Dept> list();

    /**
     * 根据部门id删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    void save(Dept dept);

    /**
     * 根据id查找部门
     * @param id
     * @return
     */
    Dept findById(Integer id);

    /**
     * 修改部门
     * @param dept
     */
    void update(Dept dept);
}
