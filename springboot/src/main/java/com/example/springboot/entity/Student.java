package com.example.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName(value = "student")
@ApiModel(value = "学生实体")
public class Student {
    @Alias("学号")
    private String id;
    @Alias("学生姓名")
    private String name;
    @Alias("密码")
    private String password;
    @Alias("学生性别")
    private String gender;
    @Alias("性别")
    private Integer age;
    @Alias("学院")
    private String academy;
    @Alias("班级")
    private String className;
    @Alias("权限")
    private Integer power;
    @Alias("政治面貌")
    private String outlook;
}
