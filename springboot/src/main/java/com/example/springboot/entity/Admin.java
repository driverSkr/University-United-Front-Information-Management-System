package com.example.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName(value = "admin")
@ApiModel(value = "管理员实体")
public class Admin {
    @Alias("学号")
    private String id;
    @Alias("姓名")
    private String name;
    @Alias("密码")
    private String password;
    @Alias("性别")
    private String gender;
    @Alias("年龄")
    private Integer age;
    @Alias("学院")
    private String academy;
    @Alias("职位")
    private String position;
    @Alias("权限")
    private Integer power;
    @Alias("政治面貌")
    private String outlook;
}
