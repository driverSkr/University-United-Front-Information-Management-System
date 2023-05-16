package com.example.springboot.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Constants;
import com.example.springboot.controller.dto.UserDTO1;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Student;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.IAdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    private static final Log LOG = Log.get();

    @Override
    public Admin login(UserDTO1 userDTO) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userDTO.getId());
        queryWrapper.eq("password", userDTO.getPassword());
        Admin one;
        // 处理异常情况
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if (one != null){
            //BeanUtils.copyProperties(one,student);
            return one;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }
}
