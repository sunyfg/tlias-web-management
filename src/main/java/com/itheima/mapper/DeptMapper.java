package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询全部部门数据
     * @return
     */
    @Select("select id, name, create_time, update_time from dept")
    List<Dept> list();

    /**
     * 根据部门id删除
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 根据ID查找部门
     * @param id
     * @return
     */
    @Select("select * from dept where id = #{id}")
    Dept findById(Integer id);

    /**
     * 修改部门
     * @param dept
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
