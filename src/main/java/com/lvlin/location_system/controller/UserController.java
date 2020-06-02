package com.lvlin.location_system.controller;
import com.alibaba.fastjson.JSONObject;
import com.lvlin.location_system.service.Impl.UserServiceImpl;
import com.lvlin.location_system.template.Result;
import com.lvlin.location_system.template.ResultFactory;
import com.lvlin.location_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    //前端登录方法
    @CrossOrigin
    @RequestMapping(value = "/api/login",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    @ResponseBody()
    public Result login(@Valid @RequestBody() User user, BindingResult bindingResult){
        Result result= userServiceImpl.login(user);
        if(bindingResult.hasErrors()){
            String message = String.format("登陆失败，详细信息[%s]",bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }
        return result;
    }

    //客户端登陆方法
    @RequestMapping(value = "/api/client_login",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@RequestParam("username") String username,@RequestParam("password") String password) throws IOException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Result result = userServiceImpl.login(user);
        return result;
    }
    //客户端注册方法
    @RequestMapping(value = "/api/client_register",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    @ResponseBody()
    public Result register(@RequestParam("username") String username,@RequestParam("password") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userServiceImpl.register(user);
    }

    //查询所有用户信息
    @CrossOrigin
    @RequestMapping(value = "/api/select_all_user",produces = "application/json; charset=UTF-8")
    @ResponseBody()
    public List<User> selectAllUser(){
        return userServiceImpl.getAll();
    }

    //删除用户信息
    @CrossOrigin
    @RequestMapping(value = "/api/delete_user",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    @ResponseBody()
    public Result delete(@RequestParam("username") String username){
        Result result = userServiceImpl.deleteByName(username);
        return result;
    }
    //修改用户信息
    @CrossOrigin
    @RequestMapping(value = "/api/update_user",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    @ResponseBody()
    public Result update(@RequestParam("username")String username,@RequestParam("password") String password){
        Result result = userServiceImpl.changePassword(username,password);
        return result;
    }
}
