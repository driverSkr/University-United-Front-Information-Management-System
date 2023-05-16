package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Opinions;
import com.example.springboot.mapper.OpinionsMapper;
import com.example.springboot.service.IOpinionsService;
import org.springframework.stereotype.Service;

@Service
public class OpinionsServiceImpl extends ServiceImpl<OpinionsMapper, Opinions> implements IOpinionsService {
}
