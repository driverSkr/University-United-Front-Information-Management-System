package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Knowledge;
import com.example.springboot.service.IKnowledgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/knowledge")
@Api(tags = "统战知识模块")
public class KnowledgeController {

    @Resource
    private IKnowledgeService knowledgeService;

    @GetMapping("/list")
    @ApiOperation(value = "获取所有统战春秋title")
    public Result list(){
        List<Knowledge> list = knowledgeService.list();
        return Result.success(list);
    }
}
