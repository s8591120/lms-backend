package com.example.pojo;


import lombok.Data;

@Data
public class StudentQueryParam {
    private Integer page = 1;  //頁碼
    private Integer pageSize = 10; //每頁展示記錄數
    private String name; //姓名
    private Integer degree; //最高學歷
    private Integer clazzId; //班級id
}
