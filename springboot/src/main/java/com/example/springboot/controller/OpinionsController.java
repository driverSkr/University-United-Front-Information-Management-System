package com.example.springboot.controller;

import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Opinions;
import com.example.springboot.service.IOpinionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/opinions")
@RestController
@Api(tags = "意见反馈模块")
public class OpinionsController {

    @Resource
    private IOpinionsService opinionsService;

    @PostMapping("/post")
    @ApiOperation(value = "提交意见")
    public Result post(@RequestBody Opinions opinions) {

        System.out.println(opinions);
        boolean save = opinionsService.save(opinions);
        if (save) return Result.success();
        else return Result.error(Constants.CODE_600,"不好意思，提交失败！");
    }
}