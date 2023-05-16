package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.UserDTO;
import com.example.springboot.entity.User;
import com.example.springboot.service.IAdminService;
import com.example.springboot.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理接口")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("/index")
    @ApiOperation(value = "index用户登录")
    public boolean login(@RequestBody User user){
        System.out.println(user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(user.getUsername())){
            queryWrapper.eq("username",user.getUsername());
        }
        User one = userService.getOne(queryWrapper);
        if(one != null){
            return user.getPassword().equals(one.getPassword());
        }else return false;

    }

    @PostMapping("/login")
    @ApiOperation(value = "login用户登录")
    public Result login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        return Result.success(dto);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public boolean register(@RequestBody User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(user.getUsername())){
            queryWrapper.like("username",user.getUsername());
        }
        User one = userService.getOne(queryWrapper);
        if (one == null){
            return userService.save(user);
        }else return false;
    }

    @GetMapping("/select")
    @ApiOperation(value = "查询所有数据")
    public Result hello(){
        return Result.success(userService.list());
    }

    @PostMapping("/insert")
    @ApiOperation(value = "新增或更新")
    public Result save(@RequestBody User user){
        return Result.success(userService.saveOrUpdate(user));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除用户")
    //@PathVariable该注解传入{id}的值
    public Result delete(@PathVariable Integer id){
        return Result.success(userService.removeById(id));
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(userService.removeByIds(ids));
    }
    //自定义分页查询
    /*接口路径：/user/page?pageNum=1&pageSize=10
    * 通过@RequestParam接收接口路径里的pageNum和pageSize数据，传入到对应的参数
    * (pageNum-1)*pageSize:开头的位置
    * pageSize:步长
    * */
    /*@GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public Map<String,Object> findPage(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String username,
                                       @RequestParam String email,
                                       @RequestParam String address){
        Map<String,Object> res = new HashMap<>();
        pageNum = (pageNum-1)*pageSize;
        Integer total = userMapper.selectTotal(username,email,address);
        List<User> data = userMapper.selectPage(pageNum, pageSize,username,email,address);

        res.put("total",total);
        res.put("data",data);

        return res;
    }*/

    //mybatis-plus方式实现分页
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public Result findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address){
        IPage<User> page = new Page<>(pageNum,pageSize);
        //添加查询条件 条件语句
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if(!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if(!"".equals(address)){
            queryWrapper.like("address",address);
        }
        //sql拼接or语句
        //queryWrapper.or().like("address",address);

        //倒序
        queryWrapper.orderByDesc("id");
        return Result.success(userService.page(page,queryWrapper));
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<User> list = userService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        /*writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("nickname", "昵称");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");*/
        //writer.addHeaderAlias("createTime", "创建时间");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    /**
     * excel 导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    @ApiOperation(value = "导入")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        /*List<List<Object>> list = reader.read(1);//指定从第几行开始读取，忽略第0行的中文表头

        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
            User user = new User();
            user.setUsername(row.get(0).toString());
            user.setPassword(row.get(1).toString());
            user.setNickname(row.get(2).toString());
            user.setEmail(row.get(3).toString());
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());
            users.add(user);
        }*/

        userService.saveBatch(list);
        return true;
    }
}
