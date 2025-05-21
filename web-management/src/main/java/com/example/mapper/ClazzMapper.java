package com.example.mapper;


import com.example.pojo.Clazz;
import com.example.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 班級條件查詢
     */
    public List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     *根據ID刪除班級
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增班級
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject, create_time, update_time) " +
            "VALUES (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void add(Clazz clazz);

    /**
     *根據ID查詢班級
     */
    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);

    /**
     * 修改班級
     */
    void update(Clazz clazz);

    /**
     * 查詢所有班級
     */
    @Select("select * from clazz")
    List<Clazz> findAll();
}
