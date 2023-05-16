package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.OfferAdvice;
import com.example.springboot.mapper.OfferAdviceMapper;
import com.example.springboot.service.IOfferAdviceService;
import org.springframework.stereotype.Service;

@Service
public class OfferAdviceImpl extends ServiceImpl<OfferAdviceMapper, OfferAdvice> implements IOfferAdviceService {
}
