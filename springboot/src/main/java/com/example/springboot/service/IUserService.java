package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.User;

public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);
}
