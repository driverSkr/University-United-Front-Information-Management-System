package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper该注解可代替SpringbootApplication里的@MapperScan,都是将该接口注入到spring池里
//使用Mybatis-plus需要配置BaseMapper<User>
public interface UserMapper extends BaseMapper<User> {

   /* //查询所有用户
    @Select("SELECT * from sys_user")
    List<User> findAll();

    //插入一个用户
    @Insert("INSERT INTO sys_user(username,password,nickname,email,phone,address) VALUES (#{username},#{password},#{nickname},#{email},#{phone},#{address})")
    int insert(User user);

    //只在选项不为空的时候更新相应用户项
    int update(User user);

    //通过id删除用户
    @Delete("delete from sys_user where id = #{id}")
    int deleteById(@Param("id") Integer id);

    //分页查询
    @Select("select * from sys_user where username like concat('%',#{username},'%') and email like concat('%',#{email},'%') and address like concat('%',#{address},'%') limit #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum,Integer pageSize,String username,String email,String address);

    //查询总条数
    @Select("select count(*) from sys_user where username like concat('%',#{username},'%') and email like concat('%',#{email},'%') and address like concat('%',#{address},'%')")
    Integer selectTotal(String username,String email,String address);*/
}
