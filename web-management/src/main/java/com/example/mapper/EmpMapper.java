package com.example.mapper;


import com.example.pojo.Emp;
import com.example.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 員工訊息
 */
@Mapper
public interface EmpMapper {
//---------------------------------------------原始分頁查詢方式
    /*
     * 查詢總記錄數
     */

    /**
     * 條件查詢員工訊息
     */
    public List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增員工基本訊息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("Insert Into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "VALUES (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 根據ID批量刪除員工基本訊息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根據ID查詢員工訊息及工作經歷
     */
    Emp getById(Integer id);

    /**
     *根據ID修改員工基本訊息
     */
    void updateById(Emp emp);

    /**
     * 統計員工職位人數
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpByData();

    /**
     * 統計員工性別人數
     */
    @MapKey("gender")
    List<Map<String, Object>> countGenderData();

    /**
     * 登錄-根據用戶名和密碼查詢員工訊息
     */
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp SelectByUsernameAndPassword(Emp emp);

    /**
     * 查詢所有員工
     */
    @Select("select * from emp")
    List<Emp> findAll();
}
