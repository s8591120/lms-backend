package com.example.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clazz {
    private Integer id; //ID
    private String name; //班級姓名
    private String room; //班級教室
    private LocalDate beginDate; //開課時間
    private LocalDate endDate; //結課時間
    private Integer masterId; //班導師
    private Integer subject; //學科
    private LocalDateTime createTime; //創建時間
    private LocalDateTime updateTime; //修改時間

    private String masterName; //班導師姓名
    private String status; //班級狀態 - 未開班, 在讀 ,已結課
}
