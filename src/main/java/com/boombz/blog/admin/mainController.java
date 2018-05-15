package com.boombz.blog.admin;


import com.boombz.blog.domain.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-04
 **/


@RestController
@RequestMapping("/admin")
public class mainController {



    @GetMapping
    public ModelAndView index(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("/users/login");
        }
        User user = (User) session.getAttribute("user");
        System.out.println(user.getRole());
        if (!user.getRole().equals("2")) {
            return new ModelAndView("/error/role");
        }
        return new ModelAndView("adminindex/index");
    }





}
