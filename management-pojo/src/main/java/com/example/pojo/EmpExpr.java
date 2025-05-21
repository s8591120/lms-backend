package com.example.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * 工作經歷
 */
@Data
public class EmpExpr {
    private Integer Id;//ID
    private Integer empId;//員工ID
    private LocalDate begin;//開始時間
    private LocalDate end;//結束時間
    private String company;//公司名稱
    private String job;//職位
}
