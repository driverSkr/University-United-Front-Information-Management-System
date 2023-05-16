package com.example.springboot.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.*;
import com.example.springboot.service.INewsService;
import com.example.springboot.service.IOfferAdviceService;
import com.example.springboot.service.IOpinionsService;
import com.example.springboot.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Api(tags = "管理员模块")
public class AdminController {

    @Resource
    private IStudentService studentService;
    @Resource
    private IOfferAdviceService offerAdviceService;
    @Resource
    private IOpinionsService opinionsService;
    @Resource
    private INewsService newsService;

    @PostMapping("/upload")
    @ApiOperation(value = "新闻添加")
    public Result upload(@RequestBody News news){
        return newsService.upload(news);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "学生新增或更新")
    public Result save(@RequestBody Student student){
        return Result.success(studentService.saveOrUpdate(student));
    }

    @PostMapping("/offerInsert")
    @ApiOperation(value = "建言献策，新增或更新")
    public Result offerSave(@RequestBody OfferAdvice offerAdvice){
        return Result.success(offerAdviceService.saveOrUpdate(offerAdvice));
    }

    @PostMapping("/opinionInsert")
    @ApiOperation(value = "意见反馈，新增或更新")
    public Result opinionSave(@RequestBody Opinions opinions){
        return Result.success(opinionsService.saveOrUpdate(opinions));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除用户")
    //@PathVariable该注解传入{id}的值
    public Result delete(@PathVariable String id){
        return Result.success(studentService.removeById(id));
    }

    @DeleteMapping("/offerDelete/{id}")
    @ApiOperation(value = "建言献策，根据id删除用户")
    //@PathVariable该注解传入{id}的值
    public Result offerDelete(@PathVariable Integer id){
        return Result.success(offerAdviceService.removeById(id));
    }

    @DeleteMapping("/opinionDelete/{id}")
    @ApiOperation(value = "意见反馈，根据id删除用户")
    //@PathVariable该注解传入{id}的值
    public Result opinionDelete(@PathVariable Integer id){
        return Result.success(opinionsService.removeById(id));
    }

    @DeleteMapping("/delete/batch")
    @ApiOperation(value = "批量删除")
    public Result deleteBatch(@RequestBody List<String> ids){
        return Result.success(studentService.removeByIds(ids));
    }

    @DeleteMapping("/offerDelete/batch")
    @ApiOperation(value = "建言献策，批量删除")
    public Result offerDeleteBatch(@RequestBody List<Integer> ids){
        return Result.success(offerAdviceService.removeByIds(ids));
    }

    @DeleteMapping("/opinionDelete/batch")
    @ApiOperation(value = "意见反馈，批量删除")
    public Result opinionDeleteBatch(@RequestBody List<Integer> ids){
        return Result.success(opinionsService.removeByIds(ids));
    }

    //mybatis-plus方式实现分页
    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String id,
                           @RequestParam(defaultValue = "") String className){
        IPage<Student> page = new Page<>(pageNum,pageSize);
        //添加查询条件 条件语句
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        if(!"".equals(id)){
            queryWrapper.like("id",id);
        }
        if(!"".equals(className)){
            queryWrapper.like("class_name",className);
        }
        //sql拼接or语句
        //queryWrapper.or().like("address",address);

        //倒序
        //queryWrapper.orderByDesc("id");
        return Result.success(studentService.page(page,queryWrapper));
    }

    //mybatis-plus方式实现分页
    @GetMapping("/offerPage")
    @ApiOperation(value = "建言献策，分页查询")
    public Result offerFindPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String title,
                           @RequestParam(defaultValue = "") String phone){
        IPage<OfferAdvice> page = new Page<>(pageNum,pageSize);
        //添加查询条件 条件语句
        QueryWrapper<OfferAdvice> queryWrapper = new QueryWrapper<>();
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        if(!"".equals(title)){
            queryWrapper.like("title",title);
        }
        if(!"".equals(phone)){
            queryWrapper.like("phone",phone);
        }
        //sql拼接or语句
        //queryWrapper.or().like("address",address);

        //倒序
        //queryWrapper.orderByDesc("id");
        return Result.success(offerAdviceService.page(page,queryWrapper));
    }

    //mybatis-plus方式实现分页
    @GetMapping("/opinionPage")
    @ApiOperation(value = "意见反馈，分页查询")
    public Result opinionFindPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "") String type,
                                @RequestParam(defaultValue = "") String phone){
        IPage<Opinions> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Opinions> queryWrapper = new QueryWrapper<>();
        if(!"".equals(name)){
            queryWrapper.like("name",name);
        }
        if(!"".equals(type)){
            queryWrapper.like("type",type);
        }
        if(!"".equals(phone)){
            queryWrapper.like("phone",phone);
        }
        return Result.success(opinionsService.page(page,queryWrapper));
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Student> list = studentService.list();
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
        String fileName = URLEncoder.encode("学生信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    @GetMapping("/offerExport")
    @ApiOperation(value = "建言献策导出")
    public void offerExport(HttpServletResponse response) throws Exception {

        List<OfferAdvice> list = offerAdviceService.list();
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("学生信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }

    @GetMapping("/opinionExport")
    @ApiOperation(value = "意见反馈导出")
    public void opinionExport(HttpServletResponse response) throws Exception {

        List<Opinions> list = opinionsService.list();
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("学生信息", "UTF-8");
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
        List<Student> list = reader.readAll(Student.class);

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

        studentService.saveBatch(list);
        return true;
    }

    @PostMapping("/offerImport")
    @ApiOperation(value = "建言献策导入")
    public Boolean offerImp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        List<OfferAdvice> list = reader.readAll(OfferAdvice.class);

        offerAdviceService.saveBatch(list);
        return true;
    }

    @PostMapping("/opinionImport")
    @ApiOperation(value = "意见反馈导入")
    public Boolean opinionImp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        List<Opinions> list = reader.readAll(Opinions.class);

        opinionsService.saveBatch(list);
        return true;
    }
}
