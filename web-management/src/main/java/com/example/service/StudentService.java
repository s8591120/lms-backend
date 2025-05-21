package com.example.service;

import com.example.pojo.PageResult;
import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    /**
     * 條件分頁查詢
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 刪除學生
     */
    void delete(List<Integer> ids);

    /**
     * 新增學生
     */
    void save(Student student);

    /**
     * 根據ID查詢學生
     */
    Student getById(Integer id);

    /**
     * 修改學生
     */
    void update(Student student);

    /**
     * 違規處理
     */
    void violationHandle(Integer id, Integer score);
}
