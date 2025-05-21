package com.example.service;

import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import com.example.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    /**
     * 分頁查詢
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);


    /**
     * 根據ID刪除班級
     */
    void deleteById(Integer id);


    /**
     * 新增班級
     */
    void add(Clazz clazz);

    /**
     *根據ID查詢班級
     */
    Clazz getById(Integer id);

    /**
     * 修改班級
     */
    void update(Clazz clazz);

    /**
     * 查詢所有班級
     */
    List<Clazz> findAll();
}
