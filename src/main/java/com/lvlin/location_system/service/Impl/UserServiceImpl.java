package com.lvlin.location_system.service.Impl;

import com.lvlin.location_system.dao.UserDao;
import com.lvlin.location_system.entity.User;
import com.lvlin.location_system.service.UserService;
import com.lvlin.location_system.template.Result;
import com.lvlin.location_system.template.ResultCode;
import com.lvlin.location_system.template.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //登录方法

    @Override
    public Result login(User user) {
        if(user.getUsername()==null){
            return ResultFactory.buildResult(ResultCode.FAIL,"用户名不可为空",user);
        }else if(user.getPassword()==null){
            return ResultFactory.buildResult(ResultCode.FAIL,"密码不可为空",user);
        }
        User user1=this.userDao.selectByName(user.getUsername());
        Result result=new Result();
        if(user1!=null){
            if(user1.getPassword().equals(user.getPassword())){
                result=ResultFactory.buildResult(ResultCode.SUCCESS,"登陆成功",user);
            }else{
                result=ResultFactory.buildResult(ResultCode.FAIL,"密码输入有误，请重新输入",user);
            }
        }else{
            result=ResultFactory.buildResult(ResultCode.FAIL,"你输入的用户不存在",user);
        }
        return result;
    }

    //注册方法
    @Override
    public Result register(User user) {
        Result result = new Result();
        if(user.getUsername()==null){
            return ResultFactory.buildResult(ResultCode.FAIL,"用户名不可为空",user);
        }else if(user.getPassword()==null){
            return ResultFactory.buildResult(ResultCode.FAIL,"密码不可为空",user);
        }
        if(userDao.selectByName(user.getUsername())!=null){
            return ResultFactory.buildResult(ResultCode.FAIL,"用户名已经注册，请重新输入",user);
        }
        userDao.insert(user);
        result=ResultFactory.buildResult(ResultCode.SUCCESS,"注册成功！",user);
        return result;
    }

    //删除一条用户信息
    @Override
    public Result deleteByName(String username) {
        Result result = new Result();
        if(userDao.selectByName(username)==null){
            return ResultFactory.buildResult(ResultCode.FAIL,"用户名不存在，请重新输入",null);
        }
        userDao.deleteByName(username);
        result=ResultFactory.buildResult(ResultCode.SUCCESS,"删除成功！",null);
        return result;
    }

    @Override
    public Result changePassword(String username, String password) {
        userDao.updateByName(username,password);
        Result result = new Result();
        result=ResultFactory.buildResult(ResultCode.SUCCESS,"修改成功！",null);
        return result;
    }

    @Override
    public List<User> getAll() {
        return userDao.selectAll();
    }

}
