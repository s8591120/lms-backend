package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import com.example.pojo.LoginInfo;
import com.example.pojo.PageResult;
import org.apache.ibatis.annotations.Insert;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分頁查詢
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增員工
     */
    void save(Emp emp);

    /**
     * 刪除員工
     */
    void delete(List<Integer> ids);

    /**
     *根據ID查詢員工訊息
     */
    Emp getInfo(Integer id);

    /**
     * 修改員工
     */
    void update(Emp emp);

    /**
     * 登錄
     */
    LoginInfo login(Emp emp);

    /**
     * 查詢所有員工
     */
    List<Emp> findAll();
}
