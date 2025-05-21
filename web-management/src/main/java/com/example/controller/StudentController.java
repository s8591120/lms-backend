package com.example.controller;


import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 學生管理
 */
@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 條件分頁查詢
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("條件分頁查詢:{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 刪除學生
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("刪除學生:{}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 新增學生
     */
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("新增學生:{}", student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 根據ID查詢學生
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根據ID查詢學生:{}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 修改學生
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改學生:{}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 違規處理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id ,@PathVariable Integer score){
        log.info("違規處理:{},{}", id, score);
        studentService.violationHandle(id,score);
        return Result.success();
    }
}
