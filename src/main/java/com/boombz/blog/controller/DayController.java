package com.boombz.blog.controller;

import com.boombz.blog.domain.Comment;
import com.boombz.blog.domain.Day;
import com.boombz.blog.domain.User;
import com.boombz.blog.serviceTest.DayServiceImpl;
import com.boombz.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05
 **/
@Controller
@RequestMapping("/day")
public class DayController {

    @Autowired
    private DayServiceImpl dayService;

    @GetMapping
    public ModelAndView list(@RequestParam(value = "async", required = false) boolean async,
                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize,
                             Model model,
                             HttpSession session) {

        if (session.getAttribute("user") == null) {
            return new ModelAndView("/users/login");
        }

        User user = (User) session.getAttribute("user");


        if (async == true) {
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            ServerResponse<Page<Day>> response = dayService.findAllDayByGroupId(user.getGroupid(),pageable);
            Page<Day> days = response.getData();
            List<Day> list = days.getContent();// 当前所在页面数据列表
            model.addAttribute("title", "纪念日");
            model.addAttribute("page", days);
            model.addAttribute("daylist", list);
        }
        return new ModelAndView(async == true ? "day/daylist :: #daylist" : "day/day", "Model", model);
    }



}
