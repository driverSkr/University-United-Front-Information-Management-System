package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.config.MarkdownUtils;
import com.example.springboot.entity.News;
import com.example.springboot.mapper.NewsMapper;
import com.example.springboot.service.INewsService;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {
    @Override
    public Result upload(News news) {
        String title = news.getTitle();
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title);
        News one = getOne(wrapper);
        if(one == null){
            String toHtml = MarkdownUtils.markdownToHtml(news.getText());
            news.setText(toHtml);
            boolean save = save(news);
            if (save) return Result.success();
            else return Result.error(Constants.CODE_500,"系统异常，插入失败");
        }else return Result.error(Constants.CODE_600,"新闻内容存在，无需插入");
    }
}
