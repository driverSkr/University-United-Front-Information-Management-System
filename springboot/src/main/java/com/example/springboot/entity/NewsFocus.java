package com.example.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@TableName(value = "news_focus")
@ToString
@ApiModel(value = "新闻-热点关注")
public class NewsFocus {
    @Alias("标题")
    private String title;
    @Alias("内容")
    private String text;
    @Alias("作者")
    private String author;
    @Alias("时间")
    private LocalDateTime createTime;
}
