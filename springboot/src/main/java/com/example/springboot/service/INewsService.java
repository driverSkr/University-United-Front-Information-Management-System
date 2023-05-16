package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.common.Result;
import com.example.springboot.entity.News;

public interface INewsService extends IService<News> {
    Result upload(News news);
}
