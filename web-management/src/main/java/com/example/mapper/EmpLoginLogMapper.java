package com.example.mapper;

import com.example.pojo.EmpLoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpLoginLogMapper {
    @Insert("INSERT INTO emp_login_log(username, password, login_time, is_success, jwt, cost_time) " +
            "VALUES(#{username}, #{password}, #{loginTime}, #{isSuccess}, #{jwt}, #{costTime})")
    public void insert(EmpLoginLog Log);
}
