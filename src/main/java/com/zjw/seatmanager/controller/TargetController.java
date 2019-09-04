package com.zjw.seatmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class TargetController {
//    @RequestMapping("/index")
//    public String index(){
//        return "/index";
//    }
//    @RequestMapping("/login")
//    public String login(){
//        return "/templates/page/login/login";
//    }
    @RequestMapping("/main")
    public String main(){
        return "/templates/page/main";
    }
    @RequestMapping("/changePwd")
    public String changePwd(){
        return "/templates/page/user/changePwd";
    }
    @RequestMapping("/userInfo")
    public String userInfo(){
        return "/templates/page/user/userInfo";
    }
    @RequestMapping("/newsList")
    public String newsList(){
        return "/templates/page/news/newsList";
    }
    @RequestMapping("/linksList")
    public String linksList(){
        return "/templates/page/links/linksList";
    }
    @RequestMapping("/systemParameter")
    public String systemParameter(){
        return "/templates/page/systemParameter/systemParameter";
    }
    @RequestMapping("/404")
    public String forofor(){
        return "/templates/page/404";
    }

}
