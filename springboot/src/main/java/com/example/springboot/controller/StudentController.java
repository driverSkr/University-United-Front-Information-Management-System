package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.UserDTO1;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.service.IAdminService;
import com.example.springboot.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
@Api(tags = "学生模块")
public class StudentController {

    @Resource
    private IStudentService studentService;
    @Resource
    private IAdminService adminService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result login(@RequestBody UserDTO1 userDTO){
        System.out.println(userDTO);
        String id = userDTO.getId();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(id) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        Object dto;
        if (userDTO.getType().equals("true")){
            dto = adminService.login(userDTO);
        }else {
            dto = studentService.login(userDTO);
        }
        return Result.success(dto);
    }

    @PostMapping("/register")
    @ApiOperation(value = "学生注册")
    public boolean register(@RequestBody Student student){
        return studentService.register(student);
    }
}
