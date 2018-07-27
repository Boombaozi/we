package com.boombz.blog.controller;


import com.boombz.blog.domain.Comment;
import com.boombz.blog.domain.User;
import com.boombz.blog.service.CommentServiceImpl;
import com.boombz.blog.service.UserServiceImpl;
import com.boombz.blog.util.ServerResponse;
import com.boombz.blog.vo.CommentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestController
@RequestMapping("/comment")
public class CommentController {

    private Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private CommentServiceImpl commentService;

    private UserServiceImpl userService;

    /**
    * @description: /comment :Post  如果非异步请求，返回页面主体，然后通过JS异步请求，返回要异步加载的列表
    *
    * @author:boombaozi.com
    **/
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
        logger.info("请求的页面:{},页面大小:{}", pageIndex, pageSize);

        if (async == true) {
            logger.info("async:{}", async);
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            ServerResponse<Page<Comment>> response = commentService.findCommentByGroupId(user.getGroupid(), pageable);
            Page<Comment> comments = response.getData();
            logger.info("总数:{}", comments.getTotalElements());
            List<Comment> list = comments.getContent();// 当前所在页面数据列表
            model.addAttribute("title", "留言板");
            model.addAttribute("page", comments);
            model.addAttribute("commentList", list);
        }
        return new ModelAndView(async == true ? "comment/list1 :: #mainContainerRepleace" : "comment/index", "Model", model);
    }




    @PostMapping("/add")
    public ModelAndView addComment(Comment comment, HttpSession session, HttpServletRequest request){
        if (session.getAttribute("user") == null) {
            return new ModelAndView("/users/login");
        }
      ServerResponse<Comment> serverResponse=commentService.addComment(comment,session,request);
      if(serverResponse.isSuccess()){
          return new ModelAndView("redirect:/comment");
      }else {
          return new ModelAndView("redirect:/comment");
      }

    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteComment(@PathVariable("id") Integer id,HttpSession session){
        if (session.getAttribute("user") == null) {
            return new ModelAndView("/users/login");
        }
        ServerResponse<Comment> serverResponse =commentService.deleteCommentById(id);
        System.out.println(id);
        if(serverResponse.isSuccess()){
            return new ModelAndView("redirect:/comment");
        }else {
            return new ModelAndView("redirect:/comment");
        }

    }
    @GetMapping("/recover/{id}")
    public ModelAndView recoverComment(@PathVariable("id") Integer id,HttpSession session){
        if (session.getAttribute("user") == null) {
            return new ModelAndView("/users/login");
        }
        ServerResponse<Comment> serverResponse =commentService.recoverComment(id);
        if(serverResponse.isSuccess()){
            return new ModelAndView("redirect:/comment/deletelist");
        }else {
            return new ModelAndView("redirect:/comment/deletelist");
        }

    }

    //已经删除的留言
    @GetMapping("/deletelist")
    public ModelAndView deletelist(@RequestParam(value = "async", required = false) boolean async,
                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                             @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize,
                             Model model,
                             HttpSession session) {

        if (session.getAttribute("user") == null) {
            return new ModelAndView("/users/login");
        }

        User user = (User) session.getAttribute("user");
        logger.info("请求的页面:{},页面大小:{}", pageIndex, pageSize);

        if (async == true) {
            logger.info("async:{}", async);
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            ServerResponse<Page<Comment>> response = commentService.findCommentByGroupId2(user.getGroupid(), pageable);
            Page<Comment> comments = response.getData();
            logger.info("总数:{}", comments.getTotalElements());
            List<Comment> list = comments.getContent();// 当前所在页面数据列表
            model.addAttribute("title", "留言板回收站");
            model.addAttribute("page", comments);
            model.addAttribute("commentlist", list);
        }
        return new ModelAndView(async == true ? "comment/commentlist2 :: #commentlist" : "comment/comment2", "Model", model);
    }

    //真正删除一个留言
    @GetMapping("/delete2/{id}")
    public ModelAndView realdeleteComment(@PathVariable("id") Integer id,HttpSession session){
        if (session.getAttribute("user") == null) {
            return new ModelAndView("/users/login");
        }
        ServerResponse<Comment> serverResponse =commentService.realdeleteCommentById(id);
        if(serverResponse.isSuccess()){
            return new ModelAndView("redirect:/comment/deletelist");
        }else {
            return new ModelAndView("redirect:/comment/deletelist");
        }

    }

}
