package com.boombz.blog.admin;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


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
    public ModelAndView index() {

        return new ModelAndView("adminindex/index");
    }





}
