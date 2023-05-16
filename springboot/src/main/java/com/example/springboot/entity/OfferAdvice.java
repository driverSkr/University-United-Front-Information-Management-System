package com.example.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(value = "建言献策对象")
@TableName(value = "offer_advice")
public class OfferAdvice {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Alias("姓名")
    private String name;
    @Alias("标题")
    private String title;
    @Alias("文本")
    private String text;
    @Alias("手机号")
    private String phone;
    @Alias("政治面貌")
    private String outlook;
    @Alias("是否匿名")
    private String anonymous;
    @Alias("建议类型")
    private String type;
}
