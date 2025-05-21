package com.example.controller;


import com.example.pojo.OperateLog;
import com.example.pojo.OperateQueryParam;
import com.example.pojo.PageResult;
import com.example.pojo.Result;
import com.example.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/log")
@RestController
public class LogSearchController {

    @Autowired
    private ReportService reportService;

    /**
     * 分頁查詢
     */
    @GetMapping("/page")
    public Result searchLog(OperateQueryParam operateQueryParam){
        log.info("分頁查詢:{}", operateQueryParam);
        PageResult<OperateLog> pageResult = reportService.page(operateQueryParam);
        return Result.success(pageResult);
    }
}
