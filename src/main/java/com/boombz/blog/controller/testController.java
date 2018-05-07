package com.boombz.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05
 **/
@Controller
@RequestMapping("/testt")
public class testController {

    @GetMapping
    public ModelAndView aa(Model model){
        return new ModelAndView("/adminindex/newindex");
    }


}
