package com.boombz.blog.service;

import com.boombz.blog.repository.CommentRepository;
import com.boombz.blog.repository.GroupRepository;
import com.boombz.blog.repository.UserRepository;
import com.boombz.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-04
 **/
public class adminServiceImpl implements adminService{

    @Autowired
  private   CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;


    @Override
    public ServerResponse<List<Long>> findallinfo() {
       List<Long> list =new ArrayList<>();
      list.add(commentRepository.count());
      list.add(userRepository.count());
      list.add(groupRepository.count());

      return ServerResponse.createBySuccess(list);
    }
}
