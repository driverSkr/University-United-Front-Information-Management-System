package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;

//@Service
//因为UserServiceImpl继承了ServiceImpl<UserMapper,User>
//所以可以不用写这个类。但是为了规范，还是写了
public class UserService extends ServiceImpl<UserMapper,User>{
}
