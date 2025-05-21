package com.example.controller;


import com.example.pojo.ClazzOption;
import com.example.pojo.JobOption;
import com.example.pojo.Result;
import com.example.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 統計員工職位人數
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("統計員工職位人數");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 統計員工性別人數
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("統計員工性別人數");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 統計學生學歷人數
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("統計學生學歷人數");
        List<Map<String, Object>> DegreeList = reportService.getStudentDegreeData();
        return Result.success(DegreeList);
    }

    /**
     * 統計班級學生人數
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("統計班級學生人數");
        ClazzOption clazzOption = reportService.getStudentCountData();
        return Result.success(clazzOption);
    }
}
