package com.example.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@ApiModel("统战知识模块")
@TableName(value = "knowledge")
public class Knowledge {
    @Alias("理论园地")
    private String titleA;
    @Alias("正文")
    private String textA;
    @Alias("作者")
    private String author;
    @Alias("创建时间")
    private LocalDateTime createTime;
    @Alias("统战春秋")
    private String titleB;
    @Alias("正文")
    private String textB;
    @Alias("政策资料")
    private String titleC;
    @Alias("正文")
    private String textC;
}
