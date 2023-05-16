package com.example.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/*
* @TableName和@TableId是mybatis-plus写法，mybatis不需要写
* @TableField(value = "username"):绑定数据库中的某一字段
* */
@Data
//@NoArgsConstructor//无参构造
//@AllArgsConstructor//有参构造
@TableName(value = "sys_user")
@ApiModel(value = "user对象")
@ToString
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @Alias("用户名")//取中文别名，方便导出导入观看
    private String username;
    //@JsonIgnore //不把密码传入前端
    @Alias("密码")
    private String password;
    @Alias("昵称")
    private String nickname;
    @Alias("邮箱")
    private String email;
    @Alias("电话")
    private String phone;
    @Alias("地址")
    private String address;
    @Alias("创建时间")
    private LocalDateTime createTime;
    @Alias("头像")
    private String avatarUrl;
}
