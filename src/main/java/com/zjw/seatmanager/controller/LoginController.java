package com.zjw.seatmanager.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.zjw.seatmanager.entity.User;
import com.zjw.seatmanager.service.UserService;
import com.zjw.seatmanager.util.RandomValidateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;

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
    public String loginUser(String username, String password,HttpServletRequest request,HttpSession session1){
        System.out.println(username);
        System.out.println(password);
        System.out.println(userService.findUserByName(username));
        try{
            //从session中获取随机数
            String inputStr = request.getParameter("code").toString();
            System.out.println("jfdfk"+inputStr);
            String random = (String) session1.getAttribute("RANDOMVALIDATECODEKEY");
            System.out.println("jfkdjfff"+random);
            if (random == null) {
//                session1.setAttribute("err","验证码不能为空");
//                return "redirect:/login";

            }
            if (random.equals(inputStr)) {
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
            } else {

            }
        }catch (Exception e){
            e.printStackTrace();

        }

            return "redirect:/index";



    }
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            // logger.error("获取验证码失败>>>>   ", e);
        }
    }

}
