package com.example.springboot.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Constants;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.controller.dto.UserDTO1;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    private static final Log LOG = Log.get();

    @Override
    public Student login(UserDTO1 userDTO) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userDTO.getId());
        queryWrapper.eq("password", userDTO.getPassword());
        Student one;
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

    @Override
    public boolean register(Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if(!"".equals(student.getId())){
            queryWrapper.like("id",student.getId());
        }
        Student one = getOne(queryWrapper);
        if (one == null){
            return save(student);
        }else return false;
    }
}
