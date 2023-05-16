package com.example.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@TableName(value = "news")
@ApiModel(value = "新闻对象")
@ToString
public class News {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @Alias("新闻标题")
    private String title;
    @Alias("新闻作者")
    private String author;
    @Alias("新闻内容")
    private String text;
    @Alias("创建时间")
    private LocalDateTime createTime;
}
