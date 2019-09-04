package com.zjw.seatmanager.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.zjw.seatmanager.entity.User;
import com.zjw.seatmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
@Slf4j
public class LoginController extends BaseController{
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login(){
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return "/templates/page/login/login";
    }
    @RequestMapping("/index")
    public String index(){
        return "/index";
    }
    @RequestMapping("/loginUser")
    //@ResponseBody
    public String loginUser(String username, String password,HttpServletRequest request){
        System.out.println(username);
        System.out.println(password);
        System.out.println(userService.findUserByName(username));
        try{
            HttpSession session=request.getSession();
            log.info("进行账号"+username+",密码验证"+password+"......");
            UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
            System.out.println(usernamePasswordToken);
            Subject subject=SecurityUtils.getSubject();
            //完成登录
            subject.login(usernamePasswordToken);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
           // request.setAttribute("user", user);
        }catch (Exception e){
            e.printStackTrace();
        }

            return "redirect:/index";



    }
}
