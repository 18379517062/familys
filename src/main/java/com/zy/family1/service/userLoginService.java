package com.zy.family1.service;

import com.zy.family1.entity.user;
import com.zy.family1.mapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class userLoginService {

    @Autowired
    private userMapper usermapper;

    //用户登录
    public user userLogin(String username,String password){
        return usermapper.userLogin(username,password);
    }

    //注册新用户
    public int addUser(String username,String password,String email) {
        return usermapper.addUser(username, password, email);
    }

    //修改用户信息
    public int modifyUser(String id,String username,String password,String email){
        return usermapper.modifyUser(Integer.parseInt(id),username,password,email);
    }
    //查询用户列表
    public List<Map<String,Object>> queryAllUser(){
        return usermapper.queryAllUser();
    }
}
