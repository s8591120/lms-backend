package com.example.service;

import com.example.pojo.*;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 統計員工職位人數
     */
    JobOption getEmpJobData();

    /**
     * 統計員工性別人數
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 統計學生學歷人數
     */
    List<Map<String, Object>> getStudentDegreeData();

    /**
     * 統計班級學生人數
     */
    ClazzOption getStudentCountData();


    /**
     * 日誌列表查詢
     */
    PageResult<OperateLog> page(OperateQueryParam operateQueryParam);
}
