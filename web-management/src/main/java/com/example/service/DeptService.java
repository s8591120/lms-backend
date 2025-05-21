package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查詢所有的部門資料
     * @return
     */

    List<Dept> findAll();

    /**
     * 根據ID刪除部門
     */
    void deleteById(Integer id);

    /**
     *新增部門
     */
    void add(Dept dept);

    /**
     * 根據ID查詢部門數據
     */
    Dept getById(Integer id);

    /**
     * 修改部門
     * @param dept
     */
    void update(Dept dept);
}
