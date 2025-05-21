package com.example.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperateLog {
    private Integer id; //ID
    private Integer operateEmpId; //操作人ID
    private LocalDateTime operateTime; //操作時間
    private String className; //操作類名
    private String methodName; //操作的方法名
    private String methodParams; //操作的方法參數
    private String returnValue; //操作的返回值
    private Long costTime; //操作耗時

    private String operateEmpName; //操作姓名
}
