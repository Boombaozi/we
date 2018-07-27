package com.boombz.blog.repository;

import com.boombz.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    //使用用户名和密码登录
    User findUserByUsernameAndPassword(String username,String Password);

    //使用邮箱登录
    User findUserByEmailAndPassword(String email,String Password);

    Page<User> findAll(Pageable page);

    User findUserByEmail(String email);

    Page<User> findUsersByGroupid(Integer groupid,Pageable pageable);

    User findById(Integer id);

    Page<User> findAllByGroupid(Integer id,Pageable pageable);

    User findUserByCode(String code);

    User findUserByUsername(String username);


}
