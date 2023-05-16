package com.example.springboot.controller.dto;

import lombok.Data;

/**
 * 接受前端登录请求的参数
 */
@Data
public class UserDTO1 {
    private String id;
    private String password;
    private String type;
}
