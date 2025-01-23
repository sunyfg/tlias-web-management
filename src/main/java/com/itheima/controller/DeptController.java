package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");

        List<Dept> deptList = deptService.list();
        System.out.println(deptList);

        return Result.success(deptList);
    }

    // 根据id删除部门
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("根据部门ID删除: {}", id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Dept dept) {
        log.info("新增部门: {}", dept);
        deptService.save(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        log.info("根据ID查询部门: {}", id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门: {}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
