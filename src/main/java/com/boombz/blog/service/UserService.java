package com.boombz.blog.service;

import com.boombz.blog.domain.User;
import com.boombz.blog.util.ServerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<User> register(User user,HttpServletRequest request);

    ServerResponse<User> register2(String code);

    ServerResponse<User> edit(User user);

    ServerResponse  addGroup(User user);

    ServerResponse<Page<User>> userList(Pageable pageable);

    ServerResponse<Page<User>> findUserbygroupid(Integer id,Pageable pageable);

    ServerResponse<User> findUserById(Integer id);

    ServerResponse<User> deleteUserById(Integer id);

    ServerResponse<User> realDeleteUserById(Integer id);

    ServerResponse<User> recoverUserById(Integer id);

    ServerResponse<Page<User>> findAllUserByGroup(HttpSession session, Pageable pageable);

}
