package com.boombz.blog.controller;

import com.boombz.blog.domain.User;


import com.boombz.blog.kafka.Producter;
import com.boombz.blog.service.UserServiceImpl;
import com.boombz.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: we
 * @description: 对用户信息进行一些操作的controller
 * @author: boombaozi.com
 * @create: 2018-04
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Producter producter;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public ModelAndView login(Model model) {
        model.addAttribute("msg", "请登录");
        return new ModelAndView("users/login", "Model", model);
    }

    @GetMapping("/loginout")
    public ModelAndView loginOut(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("msg", "未登录，无法退出！");
            return new ModelAndView("users/login", "Model", model);
        }

        producter.sendSuccess((User)session.getAttribute("user"),"退出登录","/");
        session.removeAttribute("user");

        model.addAttribute("msg", "退出登录成功！");
        return new ModelAndView("users/login", "Model", model);
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {
        model.addAttribute("msg", "注册");
        return new ModelAndView("users/register", "Model", model);
    }


    @GetMapping("/edit")
    public ModelAndView edit() {
        //跳转到
        return new ModelAndView("users/upload");
    }

    /**
     * @description: 用户登录：Post
     * @author:boombaozi.com
     **/
    @PostMapping("/login")
    public ModelAndView login(Model model, String username, String password, HttpSession session) {
        System.out.println("前端接受的数据为" + username + password);

        ServerResponse<User> response = userService.login(username, password);

        if (response.isSuccess()) {
            session.setAttribute("user", response.getData());


            producter.sendSuccessLogin(response.getData(),"用户登录成功");


            model.addAttribute(response.getData());
            return new ModelAndView("redirect:/");
        } else {
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            producter.sendErrorLogin(user,"用户登录失败");
            model.addAttribute("msg", response.getMsg());
            return new ModelAndView("users/login", "Model", model);
        }
    }

    //注册用户  存入一条新的用户数据，状态码设为“0” 表示未激活
    @PostMapping("/register")
    public ModelAndView register(Model model, User user, HttpServletRequest request) {

        ServerResponse<User> response = userService.register(user, request);

        if (response.isSuccess()) {

            producter.sendSuccess(user,"发送注册邮件",request.getRequestURI());

            model.addAttribute("msg", response.getMsg());
            return new ModelAndView("users/login", "Model", model);
        }
        model.addAttribute("msg", response.getMsg());
        return new ModelAndView("users/register", "Model", model);
    }

    //激活用户    验证邮箱链接code 是否与数据库的匹配
    @GetMapping("/register2")
    public ModelAndView register2(Model model, String code) {
        ServerResponse response = userService.register2(code);

        if (response.isSuccess()) {

           producter.sendSuccessRegister((User) response.getData(),"新用户注册");

            model.addAttribute("msg", response.getMsg());
            return new ModelAndView("users/login", "Model", model);
        } else {
            model.addAttribute("msg", response.getMsg());
            return new ModelAndView("users/login", "Model", model);
        }

    }

    //查看组内成员
    @GetMapping("/groupuser")
    public ModelAndView groupusers(@RequestParam(value = "async", required = false) boolean async,
                                   @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                   Model model,
                                   HttpSession session) {

        if (session.getAttribute("user") == null) {
            model.addAttribute("msg", "请登录");
            return new ModelAndView("users/login", "Model", model);
        }
        User user = (User) session.getAttribute("user");

        producter.sendSuccessAccess(user,"查看组内成员","/");

        if (async == true) {
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            ServerResponse<Page<User>> response = userService.findUserbygroupid(user.getGroupid(), pageable);
            Page<User> users = response.getData();
            List<User> list = users.getContent();// 当前所在页面数据列表
            System.out.println(list.get(0).toString());
            model.addAttribute("title", "user");
            model.addAttribute("page", users);
            model.addAttribute("userlist", list);
        }
        return new ModelAndView(async == true ? "users/groupuserlist :: #userlist" : "users/groupuser", "Model", model);

    }
}
