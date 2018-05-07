package com.boombz.blog.controller;

import com.boombz.blog.domain.User;


import com.boombz.blog.serviceTest.UserServiceImpl;
import com.boombz.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    private UserServiceImpl userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("users/login");
    }

    @GetMapping("/loginout")
    public ModelAndView loginOut(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("/users/login");
        }
        session.removeAttribute("user");
        return new ModelAndView("users/login");
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {
        return new ModelAndView("users/register");
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
            System.out.println(username);
            model.addAttribute(response.getData());
            return new ModelAndView("redirect:/");
        } else {
            System.out.println("登录失败");
            model.addAttribute("msg", response.getMsg());
            return new ModelAndView("redirect:/users/login");
        }
    }

    @PostMapping("/register")
    public ModelAndView register(Model model,User user) {

    ServerResponse<User> response = userService.register(user);
        if(response.isSuccess()){
            return new ModelAndView("users/login");
        }
        return new ModelAndView("redirect:/users/register");
    }


    @GetMapping("/groupuser")
    public ModelAndView groupusers(@RequestParam(value = "async", required = false) boolean async,
                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                             Model model,
                             HttpSession session) {

        if (session.getAttribute("user") == null) {
            return new ModelAndView("/users/login");
        }
        User user = (User) session.getAttribute("user");

        if (async == true) {
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            ServerResponse<Page<User>> response = userService.findUserbygroupid(user.getGroupid(),pageable);
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
