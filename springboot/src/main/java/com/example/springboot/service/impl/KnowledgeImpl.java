package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Knowledge;
import com.example.springboot.mapper.KnowledgeMapper;
import com.example.springboot.service.IKnowledgeService;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeImpl extends ServiceImpl<KnowledgeMapper, Knowledge> implements IKnowledgeService {
}
