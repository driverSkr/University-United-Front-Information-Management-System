package com.example.springboot.controller;

import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.entity.OfferAdvice;
import com.example.springboot.entity.Opinions;
import com.example.springboot.service.IOfferAdviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/advice")
@Api(tags = "建言献策模块")
public class OfferAdviceController {

    @Resource
    private IOfferAdviceService offerAdviceService;

    @PostMapping("/post")
    @ApiOperation(value = "提交意见")
    public Result post(@RequestBody OfferAdvice offerAdvice) {
        if (offerAdvice.getAnonymous().equals("true")){
            offerAdvice.setName("***");
            offerAdvice.setPhone("***");
            offerAdvice.setOutlook("***");
        }
        System.out.println(offerAdvice);
        boolean save = offerAdviceService.save(offerAdvice);
        if (save) return Result.success();
        else return Result.error(Constants.CODE_600,"不好意思，提交失败！");
    }
}
