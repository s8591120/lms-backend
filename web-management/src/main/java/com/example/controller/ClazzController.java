package com.example.controller;


import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 班級管理
 */
@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    /**
     * 分頁查詢
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分頁查詢:{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 刪除班級
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根據ID刪除班級:{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增班級
     */
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("新增班級:{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    /**
     * 根據ID查詢班級
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根據ID查詢班級:{}", id);
        Clazz clazz =  clazzService.getById(id);
        return Result.success(clazz);
    }

    /**
     * 修改班級
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班級:{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 查詢所有班級
     */
    @GetMapping("/list")
    public Result list(){
        log.info("查詢所有班級");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }
}
