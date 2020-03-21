package com.zy.family1.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zy.family1.entity.user;
import com.zy.family1.service.userLoginService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/user"})
public class userLoginController {

    @Autowired
    private userLoginService userLoginService;

    //登录页面
    @RequestMapping(value = {"/loginPage"})
    public String loginPage(){
        return "login";
    }

    @RequestMapping(value = {"/login"})
    public String userLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletRequest request,Model model){

//        if(request.getSession().getAttribute("session_user")!=null){   //已经有登录
////            model.addAttribute("logined","您已经登录，请先退出");
////        model.addAttribute("realname","赵刚");
//            return "loginError";
//        }
//        List<user> list = new ArrayList<user>();
//        user users = new user();
//        users.setId(2);
//        users.setUsername("azy");
//        users.setPassword("123");
//        users.setEmail("2279490163@qq.com");
//        list.add(users);
//        model.addAttribute("users",list);
//        model.addAttribute("realname","赵刚");
        user user = userLoginService.userLogin(username,password);

        if(user != null){                                                  //登录成功
            request.getSession().setAttribute("session_user",user);     //将用户信息放入session  用于后续的拦截器
            return "/user/userManage";
//            return  "index";
        }
        return "login";  //登录失败
    }

    //退出
    @RequestMapping(value = {"/logoutPage"})
    public String logoutPage(HttpServletRequest request){
        request.getSession().removeAttribute("session_user");
        return "index"; //返回主页
    }

    //用户修改信息
    @RequestMapping(value = {"/userManagePage"})
    public String userManagePage(){
        return "user/userManage";
    }

    @RequestMapping(value = {"/userManage"})
    public String modifyUser(@RequestParam("id")String id,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("email") String email,
                             HttpServletRequest request  ){
        int res = userLoginService.modifyUser(id,username,password,email);
        if(res == 0){
            return "loginError";  //修改失败
        }else {

            request.getSession().setAttribute("session_user",new user(Integer.parseInt(id),username,password,email));     //将用户信息放入session  用于后续的拦截器
            return "success";     //修改成功
        }
    }


    //跳转到用户注册页面
    @RequestMapping(value = {"/registerPage"})
    public String registerPage(){
        return "register";
    }


    @RequestMapping(value = {"/register"})
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("password2") String password2,
                          @RequestParam("email") String email, Model model){

        if(StringUtils.isEmpty(username)){
            return "用户名不能为空";
        }

        if(StringUtils.isEmpty(password)){
            return "密码不能为空";
        }

        if(StringUtils.isEmpty(password2)){
            return "确认密码不能为空";
        }

        if(!password.equals(password2)){
            return "两次密码不相同，注册失败！！";
        }else {
            int res = userLoginService.addUser(username,password,email);
            if(res == 0){
                model.addAttribute("error", "注册失败！可能账号已存在");
                return "loginError";
            }else {
                model.addAttribute("error", "恭喜您，注册成功！");
                return "login";
            }
        }

    }

    /**
     * 用于测试拦截器（用户是否登录，查看session）
     * 查询用户列表  http://localhost:8080/user/queryAllUser
     * @return 用户列表（json串）
     */
    @ResponseBody
    @RequestMapping(value = {"/queryAllUser"})
    public List<Map<String,Object>> queryAllUser(){

        return userLoginService.queryAllUser();
    }

    @RequestMapping(value = {"/forgetPage"})
    public  String forgetPage(){
        return "forget";
    }

}
