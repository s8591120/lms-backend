package com.example.mapper;


import com.example.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *員工工作經歷訊息
 */
@Mapper
public interface EmpExprMapper {

    /**
     * 批量保存員工工作經歷訊息
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根據員工ID批量刪除員工工作經歷
     */
    void deleteByEmpIds(List<Integer> empIds);
}
