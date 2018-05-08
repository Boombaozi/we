package com.boombz.blog.admin;

import com.boombz.blog.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
