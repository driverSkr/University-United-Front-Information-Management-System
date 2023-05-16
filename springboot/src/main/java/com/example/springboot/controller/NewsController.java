package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Knowledge;
import com.example.springboot.entity.News;
import com.example.springboot.entity.NewsFocus;
import com.example.springboot.service.INewsFocusService;
import com.example.springboot.service.INewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/news")
@Api(tags = "新闻展示")
public class NewsController {

    @Resource
    private INewsService newsService;
    @Resource
    private INewsFocusService newsFocusService;

    @GetMapping("/list")
    @ApiOperation(value = "获取热点新闻title")
    public Result list(){
        List<NewsFocus> list = newsFocusService.list();
        return Result.success(list);
    }

    //mybatis-plus方式实现分页
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String author,
                           @RequestParam(defaultValue = "") String title){
        IPage<News> page = new Page<>(pageNum,pageSize);
        //添加查询条件 条件语句
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        if(!"".equals(author)){
            queryWrapper.like("author",author);
        }
        if(!"".equals(title)){
            queryWrapper.like("title",title);
        }
        //sql拼接or语句
        //queryWrapper.or().like("address",address);

        //倒序
        //queryWrapper.orderByDesc("id");
        return Result.success(newsService.page(page,queryWrapper));
    }
}
