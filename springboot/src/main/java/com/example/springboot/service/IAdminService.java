package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.controller.dto.UserDTO1;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Student;

public interface IAdminService extends IService<Admin> {
    Admin login(UserDTO1 userDTO);
}
