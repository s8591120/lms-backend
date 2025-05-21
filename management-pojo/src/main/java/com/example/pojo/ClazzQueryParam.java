package com.example.pojo;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {

    private Integer page = 1; //頁碼
    private Integer pageSize = 10; //每頁展示紀錄數
    private String name;//班級名稱
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;//開課時間
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; //結課時間

}
