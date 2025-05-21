package com.example.pojo;

import lombok.Data;

@Data
public class OperateQueryParam {

    private Integer page = 1; //頁碼
    private Integer pageSize = 10;//每頁展示的記錄數
}
