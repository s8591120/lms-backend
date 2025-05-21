package com.example.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpLoginLog {
    private Integer id; //ID
    private String username; //登錄用戶名
    private String password; //登錄密碼
    private LocalDateTime loginTime; //登錄時間
    private Short isSuccess; //是否登錄成功 1:成功 0:失敗
    private String jwt; //下發的JWT令牌
    private Long costTime; //登錄耗時
}
