package com.boombz.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05
 **/
@Controller
@RequestMapping("error")
public class ErrorController {

    public ModelAndView error(){
        return new ModelAndView("error/index");
    }
}
