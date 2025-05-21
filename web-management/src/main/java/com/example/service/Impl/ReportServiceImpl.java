package com.example.service.Impl;

import com.example.mapper.EmpMapper;
import com.example.mapper.OperateLogMapper;
import com.example.mapper.StudentMapper;
import com.example.pojo.*;
import com.example.service.ReportService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public JobOption getEmpJobData() {
        //1. 調用mapper接口,獲取統計資料
        List<Map<String,Object>> list = empMapper.countEmpByData();
        //2.組裝結果,並返回
        List<Object> jobList = list.stream().map(datamap -> datamap.get("pos")).toList();
        List<Object> dataList = list.stream().map(datamap -> datamap.get("num")).toList();
        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.getDegreeData();
    }

    @Override
    public ClazzOption getStudentCountData() {
       List<Map<String, Object>> countList = studentMapper.getStudentCount();
       //1.查詢結果是否為空的
       if(!CollectionUtils.isEmpty(countList)){
           //把所有Map裡的Clazz的值取出來形成ClazzList清單
           List<Object> ClazzList = countList.stream().map(map ->{
               return map.get("clazz");
           }).toList();
           //把所有Map裡的num的值取出來形成DataList清單
           List<Object> DataList = countList.stream().map(map ->{
               return map.get("num");
           }).toList();
           return new ClazzOption(ClazzList,DataList);
       }
        return null;
    }

    @Override
    public PageResult<OperateLog> page(OperateQueryParam operateQueryParam) {
        PageHelper.startPage(operateQueryParam.getPage(),operateQueryParam.getPageSize());
        List<OperateLog> logList = operateLogMapper.list(operateQueryParam);
        Page<OperateLog> p = (Page<OperateLog>) logList;
        return new PageResult<OperateLog>(p.getTotal(), p.getResult());
    }
}
