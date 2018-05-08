package com.boombz.blog.admin;

import com.boombz.blog.domain.Comment;
import com.boombz.blog.domain.User;
import com.boombz.blog.service.CommentService;
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
 * @description:
 * @author: boombaozi.com
 * @create: 2018-04
 **/

@RestController
@RequestMapping("/admin/comment")
public class Comment_adController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ModelAndView comment(@RequestParam(value = "async", required = false) boolean async,
                                @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                Model model,
                                HttpSession session) {

        if (session.getAttribute("user") == null) {

            System.out.println("用户未登录");
            return new ModelAndView("/users/login");
        }
        User user = (User) session.getAttribute("user");
        System.out.println(user.getRole());
        if (!user.getRole().equals("2")) {
            System.out.println("权限错误");
            return new ModelAndView("/users/login");
        }

        if (async == true) {
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            ServerResponse<Page<Comment>> response =commentService.findAllComment(pageable);
            Page<Comment> comments = response.getData();
            List<Comment> list = comments.getContent();// 当前所在页面数据列表

            System.out.println();
            model.addAttribute("title", "comment");
            model.addAttribute("page", comments);
            model.addAttribute("commentlist", list);
        }
        return new ModelAndView(async == true ? "adminindex/commentlist :: #commentlist" : "adminindex/comment", "Model", model);
    }

    @PostMapping("/add")
    public ModelAndView addComment(Comment comment,HttpSession session,HttpServletRequest request){
        ServerResponse<Comment> serverResponse=commentService.addComment(comment,session,request);
        if(serverResponse.isSuccess()){
            return new ModelAndView("redirect:/admin/comment");
        }else {
            return new ModelAndView("redirect:/admin/comment");
        }

    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteComment(@PathVariable("id") Integer id){
        ServerResponse<Comment> serverResponse =commentService.realdeleteCommentById(id);
        if(serverResponse.isSuccess()){
            return new ModelAndView("redirect:/admin/comment");
        }else {
            return new ModelAndView("redirect:/admin/comment");
        }

    }
    @GetMapping("/recover/{id}")
    public ModelAndView recoverComment(@PathVariable("id") Integer id){
        ServerResponse<Comment> serverResponse =commentService.recoverComment(id);
        if(serverResponse.isSuccess()){
            return new ModelAndView("redirect:/admin/comment");
        }else {
            return new ModelAndView("redirect:/admin/comment");
        }

    }


}
