package com.example.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@TableName(value = "opinions")
@ApiModel(value = "意见反馈对象")
@ToString
public class Opinions {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Alias("用户名")
    private String name;
    @Alias("留言标题")
    private String title;
    @Alias("留言内容")
    private String text;
    @Alias("电子邮箱")
    private String email;
    @Alias("用户手机")
    private String phone;
    @Alias("意见类型")
    private String type;
    @Alias("政治面貌")
    private String outlook;
}
