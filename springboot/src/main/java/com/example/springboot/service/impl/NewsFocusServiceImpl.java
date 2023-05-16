package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.NewsFocus;
import com.example.springboot.mapper.NewsFocusMapper;
import com.example.springboot.service.INewsFocusService;
import org.springframework.stereotype.Service;

@Service
public class NewsFocusServiceImpl extends ServiceImpl<NewsFocusMapper, NewsFocus> implements INewsFocusService {
}
