package com.boombz.blog.admin;

import com.boombz.blog.domain.Comment;
import com.boombz.blog.domain.User;
import com.boombz.blog.serviceTest.UserService;
import com.boombz.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-04
 **/
@RestController
@RequestMapping("/admin/user")
public class User_adController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView user(@RequestParam(value = "async", required = false) boolean async,
                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                             Model model,
                             HttpSession session) {

        if (session.getAttribute("user") == null) {
            return new ModelAndView("/users/login");
        }
        User user = (User) session.getAttribute("user");
        System.out.println(user.toString());

        if (user.getRole().equals(2)) {
            return new ModelAndView("redirect:/");
        }

        if (async == true) {
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            ServerResponse<Page<User>> response = userService.userList(pageable);
            Page<User> users = response.getData();
            List<User> list = users.getContent();// 当前所在页面数据列表
            System.out.println(list.get(0).toString());
            model.addAttribute("title", "user");
            model.addAttribute("page", users);
            model.addAttribute("userlist", list);
        }
        return new ModelAndView(async == true ? "adminindex/userlist :: #userlist" : "adminindex/user", "Model", model);

    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteComment(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return new ModelAndView("redirect:/admin/user");
    }

    @GetMapping("/realdelete/{id}")
    public ModelAndView realDeleteComment(@PathVariable("id") Integer id) {
        userService.realDeleteUserById(id);
        return new ModelAndView("redirect:/admin/user");
    }

    @GetMapping("/recover/{id}")
    public ModelAndView recoverComment(@PathVariable("id") Integer id) {
        userService.recoverUserById(id);
        return new ModelAndView("redirect:/admin/user");
    }
}
