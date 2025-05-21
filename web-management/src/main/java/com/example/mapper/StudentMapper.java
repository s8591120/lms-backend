package com.example.mapper;

import com.example.pojo.Student;
import com.example.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     *班級判斷是否有學生
     */
    @Select( "select count(*) from student where clazz_id = #{id}")
    Integer countByClazzId(Integer id);

    /**
     *條件分頁查詢
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     *刪除學生
     */
    void delete(List<Integer> ids);

    /**
     * 新增學生
     */
    @Insert("insert into " +
            "student(name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id, create_time, update_time) " +
            "VALUES (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void save(Student student);

    /**
     * 根據ID查詢學生
     */
    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    /**
     * 修改學生
     */
    void update(Student student);

    /**
     * 違規處理
     */
    @Update("update student set violation_count = violation_count + 1," +
            "violation_score = GREATEST(violation_score - #{score}, 0), update_time = now()" +
            " where id = #{id}")
    void violationHandle(Integer id, Integer score);

    /**
     * 統計學生學歷人數
     */
    @MapKey("degree")
    List<Map<String, Object>> getDegreeData();

    /**
     * 統計班級學生人數
     */
    @MapKey("clazz")
    List<Map<String, Object>> getStudentCount();
}
