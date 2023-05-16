package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.controller.dto.UserDTO1;
import com.example.springboot.entity.Student;

public interface IStudentService extends IService<Student> {
    Student login(UserDTO1 userDTO1);
    boolean register(Student student);
}
