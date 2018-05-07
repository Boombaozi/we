package com.boombz.blog.admin;

import com.boombz.blog.domain.Comment;
import com.boombz.blog.domain.User;
import com.boombz.blog.repository.GroupRepository;
import com.boombz.blog.serviceTest.CommentService;
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
@RequestMapping("/admin/group")
public class group_adController {

    @Autowired
    private GroupRepository groupRepository;



}
