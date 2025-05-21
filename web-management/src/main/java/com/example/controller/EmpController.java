package com.example.controller;


import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 員工管理Controller
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;
    /**
     * 分頁查詢
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分頁查詢:{}" , empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增員工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增員工:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 刪除員工
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("刪除員工:{}" ,ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根據ID查詢員工訊息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根據ID查詢員工訊息:{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 修改員工
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改員工:{}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 查詢所有員工
     */
    @GetMapping("/list")
    public Result list(){
        log.info("查詢所有員工");
        List<Emp> empList = empService.findAll();
        return Result.success(empList);
    }
}
