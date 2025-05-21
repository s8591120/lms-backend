package com.example.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Emp {
    private Integer id;//ID,主鍵
    private String username;//用戶名
    private String password;//密碼
    private String name;//姓名
    private Integer gender;//性別,1:男,2:女
    private String phone;//手機號碼
    private Integer job;//職位,1:導師,2:講師,3:學務主任,4:教務主任,5:諮詢師
    private Integer salary;//薪資
    private String image;//圖像
    private LocalDate entryDate;//入職時間
    private Integer deptId;//關聯部門ID
    private LocalDateTime createTime;//創建時間
    private LocalDateTime updateTime;//修改時間

    //封裝部門名稱
    private String deptName;
    //封裝工作經歷訊息
    private List<EmpExpr> exprList;
}
