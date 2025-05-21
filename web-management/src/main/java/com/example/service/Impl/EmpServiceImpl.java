package com.example.service.Impl;

import com.example.mapper.EmpExprMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.*;
import com.example.service.EmpLogService;
import com.example.service.EmpService;
import com.example.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class EmpServiceImpl implements EmpService {


    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;
    /**
     * 原始分頁查詢
     * @param page //頁碼
     * @param pageSize //每頁記錄數
     * @return
     */
   /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.調用mapper,查詢總記錄數
        Long total = empMapper.count();
       //2.掉用mapper,查詢結果列表
        Integer start = (page-1)*pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
       //3.封裝結果 PageResult
        return new PageResult<Emp>(total, rows);
    }*/

    /**
     * pagehelper分頁查詢
     * @return
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.設置分頁參數(pagehelper)
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.執行查詢
        List<Emp> empList = empMapper.list(empQueryParam);
        //3.解析查詢結果,並封裝
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional //事務管理
    @Override
    public void save(Emp emp) {
        try {
            //1.保存員工基本訊息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            //2.保存員工工作經歷訊息
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //遍歷集合 為empId賦值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //紀錄操作日誌
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增員工:" + emp);
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.批量刪除員工基本訊息
        empMapper.deleteByIds(ids);

        //2.批量刪除員工工作經歷訊息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1. 根據ID修改員工基本訊息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2. 根據ID修改員工工作經歷
        //2.1 先根據員工ID刪除原有的工作經歷
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2 再添加
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr ->
                empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.SelectByUsernameAndPassword(emp);
        if(e != null){
            log.info("登錄成功,員工訊息:{}",e);
            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }
        return null;
    }

    @Override
    public List<Emp> findAll() {
        return empMapper.findAll();
    }
}
