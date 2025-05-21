package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {


    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        //System.out.println("查詢全部的部門資料");
        log.info("查詢全部的部門資料");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 刪除部門
     */
    @Log
    @DeleteMapping
    public Result delete(Integer id){
        //System.out.println("根據ID刪除部門" + id);
        log.info("根據ID刪除部門:{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部門
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("新增部門" + dept);
        log.info("新增部門:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根據ID查詢部門
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根據ID查詢部門" + id);
        log.info("根據ID查詢部門:{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部門
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部門" + dept);
        log.info("修改部門:{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}


