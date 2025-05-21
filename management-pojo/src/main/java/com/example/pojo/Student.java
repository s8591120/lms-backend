package com.example.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id; //ID
    private String name; //姓名
    private String no; //學號
    private Integer gender; //性別
    private String phone; //手機號碼
    private String idCard; //身分證字號
    private Integer isCollege; //是否來自大學: 1:是, 2:否
    private String address; //聯絡地址
    private Integer degree; //最高學歷：1: 國中，2: 高中，3: 專科，4: 大學，5: 碩士，6: 博士
    private LocalDate graduationDate; //畢業時間
    private Integer clazzId; //班級ID
    private Short violationCount; //違規次數
    private Short violationScore; //違規扣分
    private LocalDateTime createTime; //創建時間
    private LocalDateTime updateTime; //修改時間

    private String clazzName; //班級名稱
}
