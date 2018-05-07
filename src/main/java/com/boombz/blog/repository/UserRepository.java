package com.boombz.blog.repository;

import com.boombz.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByUsernameAndPassword(String username,String Password);

    Page<User> findAll(Pageable page);

    User findUserByUsername(String username);

    Page<User> findUsersByGroupid(Integer groupid,Pageable pageable);

    User findById(Integer id);

    Page<User> findAllByGroupid(Integer id,Pageable pageable);
}
